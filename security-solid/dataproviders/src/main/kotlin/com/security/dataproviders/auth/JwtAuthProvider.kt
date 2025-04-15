package com.security.dataproviders.auth

import com.security.core.domain.auth.Password
import com.security.core.domain.auth.User
import com.security.core.domain.auth.Username
import com.security.core.ports.auth.AuthProvider
import com.security.core.ports.auth.AuthResponse
import com.security.dataproviders.auth.entity.RefreshTokenEntity
import com.security.dataproviders.auth.repository.RefreshTokenJpaRepository
import com.security.dataproviders.auth.repository.UserJpaRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.Instant
import java.util.Date
import java.util.UUID
import javax.crypto.SecretKey

@Component
class JwtAuthProvider(
    private val userJpaRepository: UserJpaRepository,
    private val refreshTokenRepository: RefreshTokenJpaRepository,
    private val jwtSecretKey: SecretKey,
    private val passwordEncoder: PasswordEncoder,
    private val accessTokenExpiration: Long,
    private val refreshTokenExpiration: Long,
) : AuthProvider {
    private val key = Keys.hmacShaKeyFor(jwtSecretKey.encoded)
    private val clock = Clock.systemUTC()

    override fun authenticate(
        username: Username,
        password: Password,
    ): AuthResponse {
        val user =
            userJpaRepository.findByUsername(username.value)
                ?: throw UsernameNotFoundException("User not found")

        if (!user.isEnabled) {
            throw RuntimeException("User account is disabled")
        }

        if (!passwordEncoder.matches(password.value, user.password)) {
            throw RuntimeException("Invalid password")
        }

        return generateTokenPair(user.toDomain())
    }

    override fun logout(token: String) {
        val username = extractUsername(token) ?: return
        val user = userJpaRepository.findByUsername(username) ?: return

        refreshTokenRepository.findByUserId(user.id).forEach { tokenEntity ->
            val revokedTokenEntity =
                RefreshTokenEntity(
                    id = tokenEntity.id,
                    userId = tokenEntity.userId,
                    token = tokenEntity.token,
                    expiresAt = tokenEntity.expiresAt,
                    createdAt = tokenEntity.createdAt,
                    revoked = true,
                )
            refreshTokenRepository.save(revokedTokenEntity)
        }
    }

    override fun validateToken(token: String): Boolean {
        return try {
            val username = extractUsername(token) ?: return false
            val user = userJpaRepository.findByUsername(username) ?: return false
            user.isEnabled && !isTokenExpired(token)
        } catch (e: Exception) {
            false
        }
    }

    override fun refreshToken(refreshToken: String): AuthResponse {
        val tokenEntity =
            refreshTokenRepository.findValidToken(refreshToken)
                ?: throw RuntimeException("Invalid or expired refresh token")

        val user =
            userJpaRepository
                .findById(tokenEntity.userId)
                .orElseThrow { UsernameNotFoundException("User not found") }
                .toDomain()

        if (!user.isEnabled) {
            throw RuntimeException("User account is disabled")
        }

        val revokedTokenEntity =
            RefreshTokenEntity(
                id = tokenEntity.id,
                userId = tokenEntity.userId,
                token = tokenEntity.token,
                expiresAt = tokenEntity.expiresAt,
                createdAt = tokenEntity.createdAt,
                revoked = true,
            )
        refreshTokenRepository.save(revokedTokenEntity)

        return generateTokenPair(user)
    }

    override fun revokeRefreshToken(token: String) {
        val tokenEntity = refreshTokenRepository.findByToken(token)
        tokenEntity?.let {
            val revokedTokenEntity =
                RefreshTokenEntity(
                    id = it.id,
                    userId = it.userId,
                    token = it.token,
                    expiresAt = it.expiresAt,
                    createdAt = it.createdAt,
                    revoked = true,
                )
            refreshTokenRepository.save(revokedTokenEntity)
        }
    }

    override fun extractUsername(token: String): String? =
        try {
            val claims =
                Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .body
            claims.subject
        } catch (e: Exception) {
            null
        }

    private fun generateTokenPair(user: User): AuthResponse {
        val accessToken = generateAccessToken(user)
        val refreshToken = generateRefreshToken(user)
        return AuthResponse(accessToken, refreshToken, Instant.now(clock).plusMillis(accessTokenExpiration))
    }

    private fun generateAccessToken(user: User): String {
        val now = Date.from(Instant.now(clock))
        val expiration = Date.from(Instant.now(clock).plusMillis(accessTokenExpiration))

        return Jwts
            .builder()
            .setSubject(user.username.value)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .claim("userId", user.id.value)
            .claim("roles", user.roles)
            .signWith(key)
            .compact()
    }

    private fun generateRefreshToken(user: User): String {
        val token = UUID.randomUUID().toString()
        val now = Instant.now(clock)
        val expiration = now.plusMillis(refreshTokenExpiration)

        val tokenEntity =
            RefreshTokenEntity(
                token = token,
                userId = user.id.value,
                expiresAt = expiration,
                revoked = false,
            )

        refreshTokenRepository.save(tokenEntity)
        return token
    }

    private fun isTokenExpired(token: String): Boolean =
        try {
            val claims =
                Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .setClock { Date.from(Instant.now(clock)) }
                    .build()
                    .parseClaimsJws(token)
                    .body
            claims.expiration.before(Date.from(Instant.now(clock)))
        } catch (e: Exception) {
            true
        }
}

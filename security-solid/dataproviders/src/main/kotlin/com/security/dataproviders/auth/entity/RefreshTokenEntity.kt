package com.security.dataproviders.auth.entity

import com.security.core.domain.auth.RefreshToken
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "refresh_tokens")
data class RefreshTokenEntity(
    @Id
    @Column(name = "id", nullable = false)
    val id: String = UUID.randomUUID().toString(),
    @Column(name = "user_id", nullable = false)
    val userId: String,
    @Column(name = "token", nullable = false, unique = true)
    val token: String,
    @Column(name = "expires_at", nullable = false)
    val expiresAt: Instant,
    @Column(name = "created_at", nullable = false)
    val createdAt: Instant = Instant.now(),
    @Column(name = "revoked", nullable = false)
    val revoked: Boolean = false,
) {
    fun toDomain(): RefreshToken =
        RefreshToken(
            id = id,
            userId =
                com.security.core.domain.auth
                    .UserId(userId),
            token = token,
            expiresAt = expiresAt,
            createdAt = createdAt,
            revoked = revoked,
        )

    companion object {
        fun fromDomain(refreshToken: RefreshToken): RefreshTokenEntity =
            RefreshTokenEntity(
                id = refreshToken.id,
                userId = refreshToken.userId.value,
                token = refreshToken.token,
                expiresAt = refreshToken.expiresAt,
                createdAt = refreshToken.createdAt,
                revoked = refreshToken.revoked,
            )
    }

    fun isExpired(): Boolean = expiresAt.isBefore(Instant.now())
}

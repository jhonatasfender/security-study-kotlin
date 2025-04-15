package com.security.core.ports.auth

import com.security.core.domain.auth.Password
import com.security.core.domain.auth.RefreshToken
import com.security.core.domain.auth.Username
import java.time.Instant

interface AuthProvider {
    fun authenticate(
        username: Username,
        password: Password,
    ): AuthResponse

    fun logout(token: String)

    fun validateToken(token: String): Boolean

    fun refreshToken(refreshToken: String): AuthResponse

    fun revokeRefreshToken(token: String)

    fun extractUsername(token: String): String?
}

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: Instant,
) 

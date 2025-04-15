package com.security.usecases.auth

import com.security.core.ports.auth.AuthProvider
import com.security.usecases.ports.UseCase
import java.time.Instant

class RefreshTokenUseCase(
    private val authProvider: AuthProvider,
) : UseCase<RefreshTokenRequest, RefreshTokenResponse> {
    override fun execute(request: RefreshTokenRequest): RefreshTokenResponse {
        try {
            val authResponse = authProvider.refreshToken(request.refreshToken)
            return RefreshTokenResponse(
                accessToken = authResponse.accessToken,
                refreshToken = authResponse.refreshToken,
                expiresAt = authResponse.expiresAt,
            )
        } catch (e: Exception) {
            throw RuntimeException("Failed to refresh token: ${e.message}")
        }
    }
}

data class RefreshTokenRequest(
    val refreshToken: String,
)

data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: Instant,
)

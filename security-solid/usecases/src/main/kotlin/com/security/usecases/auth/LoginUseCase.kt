package com.security.usecases.auth

import com.security.core.domain.auth.Password
import com.security.core.domain.auth.Username
import com.security.core.ports.auth.AuthProvider
import com.security.usecases.ports.UseCase

class LoginUseCase(
    private val authProvider: AuthProvider,
) : UseCase<LoginRequest, LoginResponse> {
    override fun execute(request: LoginRequest): LoginResponse {
        val authResponse =
            authProvider.authenticate(
                username = Username(request.username),
                password = Password(request.password),
            )
        return LoginResponse(
            accessToken = authResponse.accessToken,
            refreshToken = authResponse.refreshToken,
            expiresAt = authResponse.expiresAt,
        )
    }
}

package com.security.usecases.auth

import com.security.core.ports.auth.AuthProvider
import com.security.usecases.ports.UseCase

class ValidateTokenUseCase(
    private val authProvider: AuthProvider,
) : UseCase<ValidateTokenRequest, ValidateTokenResponse> {
    override fun execute(request: ValidateTokenRequest): ValidateTokenResponse {
        val isValid = authProvider.validateToken(request.token)
        return ValidateTokenResponse(isValid)
    }
}

data class ValidateTokenRequest(
    val token: String,
)

data class ValidateTokenResponse(
    val isValid: Boolean,
)

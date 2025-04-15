package com.security.usecases.auth

import com.security.core.ports.auth.AuthProvider
import com.security.core.ports.auth.SecurityContext
import com.security.usecases.ports.UseCase

class LogoutUseCase(
    private val authProvider: AuthProvider,
    private val securityContext: SecurityContext,
) : UseCase<LogoutRequest, Unit> {
    override fun execute(request: LogoutRequest) {
        authProvider.logout(request.token)
        securityContext.clearContext()
    }
}

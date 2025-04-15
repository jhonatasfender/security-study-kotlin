package com.security.usecases.config.auth

import com.security.core.ports.auth.AuthProvider
import com.security.core.ports.auth.SecurityContext
import com.security.core.ports.auth.UserRepository
import com.security.usecases.UseCaseExecutor
import com.security.usecases.auth.*
import com.security.usecases.config.UseCaseConfigModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AuthUseCaseConfig : UseCaseConfigModule {
    @Bean
    fun loginUseCase(authProvider: AuthProvider): LoginUseCase = LoginUseCase(authProvider)

    @Bean
    fun logoutUseCase(
        authProvider: AuthProvider,
        securityContext: SecurityContext,
    ): LogoutUseCase = LogoutUseCase(authProvider, securityContext)

    @Bean
    fun validateTokenUseCase(authProvider: AuthProvider): ValidateTokenUseCase = ValidateTokenUseCase(authProvider)

    @Bean
    fun refreshTokenUseCase(authProvider: AuthProvider): RefreshTokenUseCase = RefreshTokenUseCase(authProvider)

    @Bean
    fun registerUseCase(
        userRepository: UserRepository,
        passwordEncoder: PasswordEncoder,
    ): RegisterUseCase = RegisterUseCase(userRepository, passwordEncoder)

    @Bean
    fun getUserInfoUseCase(userRepository: UserRepository): GetUserInfoUseCase = GetUserInfoUseCase(userRepository)

    override fun configure() {
        // Configurações específicas do módulo de autenticação
    }
} 
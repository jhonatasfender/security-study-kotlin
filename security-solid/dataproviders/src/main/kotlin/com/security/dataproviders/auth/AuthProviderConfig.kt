package com.security.dataproviders.auth

import com.security.core.ports.auth.AuthProvider
import com.security.dataproviders.auth.repository.RefreshTokenJpaRepository
import com.security.dataproviders.auth.repository.UserJpaRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AuthProviderConfig {
    @Bean
    fun authProvider(
        userJpaRepository: UserJpaRepository,
        refreshTokenRepository: RefreshTokenJpaRepository,
        jwtConfig: JwtConfig,
        passwordEncoder: PasswordEncoder,
    ): AuthProvider =
        JwtAuthProvider(
            userJpaRepository = userJpaRepository,
            refreshTokenRepository = refreshTokenRepository,
            jwtSecretKey = jwtConfig.jwtSecretKey(),
            passwordEncoder = passwordEncoder,
            accessTokenExpiration = jwtConfig.accessTokenExpiration,
            refreshTokenExpiration = jwtConfig.refreshTokenExpiration,
        )
}

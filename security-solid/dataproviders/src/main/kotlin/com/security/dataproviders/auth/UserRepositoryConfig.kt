package com.security.dataproviders.auth

import com.security.core.ports.auth.UserRepository
import com.security.dataproviders.auth.repository.UserJpaRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
class UserRepositoryConfig {
    @Bean
    fun userRepository(userJpaRepository: UserJpaRepository): UserRepository =
        JpaUserRepository(userJpaRepository)
} 
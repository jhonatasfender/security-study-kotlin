package com.security.presentation.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource

@Configuration
class WebSecurityConfig {
    @Bean
    fun webAuthenticationDetailsSource(): WebAuthenticationDetailsSource =
        WebAuthenticationDetailsSource()
} 
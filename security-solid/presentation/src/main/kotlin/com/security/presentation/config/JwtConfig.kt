package com.security.presentation.config

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.crypto.SecretKey

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtConfig {
    lateinit var secret: String
    var accessTokenExpiration: Long = 900000 // 15 minutes
    var refreshTokenExpiration: Long = 604800000 // 7 days

    @Bean
    fun jwtSecretKey(): SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())
}

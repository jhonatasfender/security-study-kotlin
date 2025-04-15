package com.security.usecases.auth

import java.time.Instant

data class LoginRequest(
    val username: String,
    val password: String,
)

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: Instant,
)

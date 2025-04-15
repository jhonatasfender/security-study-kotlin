package com.security.presentation.auth.dto

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: String,
)

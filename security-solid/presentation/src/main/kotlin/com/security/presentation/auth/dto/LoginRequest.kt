package com.security.presentation.auth.dto

data class LoginRequest(
    val username: String,
    val password: String,
)

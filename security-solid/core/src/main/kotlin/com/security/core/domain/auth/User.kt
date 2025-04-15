package com.security.core.domain.auth

import java.time.Instant

data class User(
    val id: UserId,
    val username: Username,
    val password: Password,
    val roles: Set<Role>,
    val isEnabled: Boolean = true,
)

data class UserId(
    val value: String,
)

data class Username(
    val value: String,
)

data class Password(
    val value: String,
)

data class RefreshToken(
    val id: String,
    val userId: UserId,
    val token: String,
    val expiresAt: Instant,
    val createdAt: Instant = Instant.now(),
    val revoked: Boolean = false,
)

enum class Role {
    USER,
    ADMIN,
}

package com.security.core.ports.auth

interface UserAccountConfig {
    val isAccountNonExpired: Boolean
    val isCredentialsNonExpired: Boolean
    val isAccountNonLocked: Boolean
} 
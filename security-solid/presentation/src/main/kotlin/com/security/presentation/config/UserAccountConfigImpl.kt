package com.security.presentation.config

import com.security.core.ports.auth.UserAccountConfig
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "user.account")
data class UserAccountConfigImpl(
    override val isAccountNonExpired: Boolean = true,
    override val isCredentialsNonExpired: Boolean = true,
    override val isAccountNonLocked: Boolean = true,
) : UserAccountConfig 

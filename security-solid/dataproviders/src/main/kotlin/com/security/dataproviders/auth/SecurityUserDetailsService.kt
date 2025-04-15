package com.security.dataproviders.auth

import com.security.core.domain.auth.Username
import com.security.core.ports.auth.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class SecurityUserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user =
            userRepository.findByUsername(Username(username))
                ?: throw UsernameNotFoundException("User not found: $username")

        val authorities = user.roles.map { SimpleGrantedAuthority("ROLE_${it.name}") }

        return User(
            user.username.value,
            user.password.value,
            user.isEnabled,
            true, // accountNonExpired
            true, // credentialsNonExpired
            true, // accountNonLocked
            authorities,
        )
    }
}

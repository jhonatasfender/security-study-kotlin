package com.security.core.ports.auth

import com.security.core.domain.auth.User
import com.security.core.domain.auth.Username

interface UserRepository {
    fun findByUsername(username: Username): User?

    fun save(user: User): User

    fun existsByUsername(username: Username): Boolean
} 

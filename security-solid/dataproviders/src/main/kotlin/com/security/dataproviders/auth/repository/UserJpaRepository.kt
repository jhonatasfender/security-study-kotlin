package com.security.dataproviders.auth.repository

import com.security.dataproviders.auth.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : JpaRepository<UserEntity, String> {
    fun findByUsername(username: String): UserEntity?

    fun existsByUsername(username: String): Boolean
}

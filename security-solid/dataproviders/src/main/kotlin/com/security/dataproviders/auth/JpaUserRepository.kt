package com.security.dataproviders.auth

import com.security.core.domain.auth.User
import com.security.core.domain.auth.Username
import com.security.core.ports.auth.UserRepository
import com.security.dataproviders.auth.entity.UserEntity
import com.security.dataproviders.auth.repository.UserJpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class JpaUserRepository(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {
    override fun findByUsername(username: Username): User? = userJpaRepository.findByUsername(username.value)?.toDomain()

    override fun save(user: User): User = userJpaRepository.save(UserEntity.fromDomain(user)).toDomain()

    override fun existsByUsername(username: Username): Boolean = userJpaRepository.existsByUsername(username.value)
}

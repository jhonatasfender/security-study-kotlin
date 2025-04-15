package com.security.dataproviders.auth.entity

import com.security.core.domain.auth.Role
import com.security.core.domain.auth.User
import com.security.core.domain.auth.UserId
import com.security.core.domain.auth.Username
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    val id: String,
    @Column(nullable = false, unique = true, length = 50)
    val username: String,
    @Column(nullable = false, length = 100)
    val password: String,
    @Column(name = "is_enabled", nullable = false)
    val isEnabled: Boolean = true,
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
    @Column(name = "role")
    val roles: Set<String> = emptySet(),
) {
    fun toDomain(): User =
        User(
            id = UserId(id),
            username = Username(username),
            password =
                com.security.core.domain.auth
                    .Password(password),
            roles = roles.map { Role.valueOf(it) }.toSet(),
            isEnabled = isEnabled,
        )

    companion object {
        fun fromDomain(user: User): UserEntity =
            UserEntity(
                id = user.id.value,
                username = user.username.value,
                password = user.password.value,
                roles = user.roles.map { it.name }.toSet(),
                isEnabled = user.isEnabled,
            )
    }
}

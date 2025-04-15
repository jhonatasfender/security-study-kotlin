package com.security.usecases.auth

import com.security.core.domain.auth.Password
import com.security.core.domain.auth.Role
import com.security.core.domain.auth.Username
import com.security.core.ports.auth.UserRepository
import com.security.usecases.ports.UseCase
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.UUID

class RegisterUseCase(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : UseCase<RegisterRequest, RegisterResponse> {
    override fun execute(request: RegisterRequest): RegisterResponse {
        val username = Username(request.username)

        if (userRepository.existsByUsername(username)) {
            throw RuntimeException("Username already exists")
        }

        val user =
            com.security.core.domain.auth.User(
                id =
                    com.security.core.domain.auth
                        .UserId(UUID.randomUUID().toString()),
                username = username,
                password = Password(passwordEncoder.encode(request.password)),
                roles = request.roles.map { Role.valueOf(it) }.toSet(),
            )

        val savedUser = userRepository.save(user)

        return RegisterResponse(
            id = savedUser.id.value,
            username = savedUser.username.value,
            roles = savedUser.roles.map { it.name },
        )
    }
}

data class RegisterRequest(
    val username: String,
    val password: String,
    val roles: List<String>,
)

data class RegisterResponse(
    val id: String,
    val username: String,
    val roles: List<String>,
)

package com.security.usecases.auth

import com.security.core.domain.auth.Username
import com.security.core.ports.auth.UserRepository
import com.security.usecases.ports.UseCase

class GetUserInfoUseCase(
    private val userRepository: UserRepository,
) : UseCase<GetUserInfoRequest, GetUserInfoResponse> {
    override fun execute(request: GetUserInfoRequest): GetUserInfoResponse {
        val user =
            userRepository.findByUsername(Username(request.username))
                ?: throw RuntimeException("User not found")

        return GetUserInfoResponse(
            id = user.id.value,
            username = user.username.value,
            roles = user.roles.map { it.name },
            isEnabled = user.isEnabled,
        )
    }
}

data class GetUserInfoRequest(
    val username: String,
)

data class GetUserInfoResponse(
    val id: String,
    val username: String,
    val roles: List<String>,
    val isEnabled: Boolean,
)

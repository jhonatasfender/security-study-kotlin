package com.security.presentation.resource.auth

import com.security.presentation.dto.auth.LoginRequestDto
import com.security.presentation.dto.auth.LoginResponseDto
import com.security.presentation.dto.auth.RefreshTokenRequestDto
import com.security.presentation.dto.auth.RefreshTokenResponseDto
import com.security.presentation.dto.auth.RegisterRequestDto
import com.security.presentation.dto.auth.RegisterResponseDto
import com.security.presentation.dto.auth.UserInfoResponseDto
import com.security.usecases.UseCaseExecutor
import com.security.usecases.auth.GetUserInfoRequest
import com.security.usecases.auth.GetUserInfoResponse
import com.security.usecases.auth.GetUserInfoUseCase
import com.security.usecases.auth.LoginRequest
import com.security.usecases.auth.LoginUseCase
import com.security.usecases.auth.LogoutRequest
import com.security.usecases.auth.LogoutUseCase
import com.security.usecases.auth.RefreshTokenRequest
import com.security.usecases.auth.RefreshTokenUseCase
import com.security.usecases.auth.RegisterRequest
import com.security.usecases.auth.RegisterUseCase
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthResourceImpl(
    private val useCaseExecutor: UseCaseExecutor,
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val registerUseCase: RegisterUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
) : AuthResource {
    override fun register(request: RegisterRequestDto): ResponseEntity<RegisterResponseDto> {
        val response =
            useCaseExecutor.execute(
                useCase = registerUseCase,
                requestDto = request,
                requestConverter = { RegisterRequest(it.username, it.password, it.roles) },
                responseConverter = { RegisterResponseDto(it.id, it.username, it.roles) },
            )
        return ResponseEntity.ok(response)
    }

    override fun login(request: LoginRequestDto): ResponseEntity<LoginResponseDto> {
        val response =
            useCaseExecutor.execute(
                useCase = loginUseCase,
                requestDto = request,
                requestConverter = { LoginRequest(it.username, it.password) },
                responseConverter = { LoginResponseDto(it.accessToken, it.refreshToken, it.expiresAt) },
            )
        return ResponseEntity.ok(response)
    }

    override fun refreshToken(request: RefreshTokenRequestDto): ResponseEntity<RefreshTokenResponseDto> {
        val response =
            useCaseExecutor.execute(
                useCase = refreshTokenUseCase,
                requestDto = request,
                requestConverter = { RefreshTokenRequest(it.refreshToken) },
                responseConverter = { RefreshTokenResponseDto(it.accessToken, it.refreshToken, it.expiresAt) },
            )
        return ResponseEntity.ok(response)
    }

    override fun logout(token: String): ResponseEntity<Unit> {
        val cleanToken = token.replace("Bearer ", "")
        logoutUseCase.execute(LogoutRequest(cleanToken))
        return ResponseEntity.ok().build()
    }

    override fun getUserInfo(): ResponseEntity<UserInfoResponseDto> {
        val authentication =
            SecurityContextHolder.getContext().authentication
                ?: throw RuntimeException("User isn't authenticated")

        val response =
            useCaseExecutor.execute<String, GetUserInfoRequest, GetUserInfoResponse, UserInfoResponseDto>(
                useCase = getUserInfoUseCase,
                requestDto = authentication.name,
                requestConverter = { GetUserInfoRequest(it) },
                responseConverter = { UserInfoResponseDto(it.id, it.username, it.roles, it.isEnabled) },
            )
        return ResponseEntity.ok(response)
    }
}

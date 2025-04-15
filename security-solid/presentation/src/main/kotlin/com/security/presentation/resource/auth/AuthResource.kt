package com.security.presentation.resource.auth

import com.security.presentation.dto.auth.LoginRequestDto
import com.security.presentation.dto.auth.LoginResponseDto
import com.security.presentation.dto.auth.RefreshTokenRequestDto
import com.security.presentation.dto.auth.RefreshTokenResponseDto
import com.security.presentation.dto.auth.RegisterRequestDto
import com.security.presentation.dto.auth.RegisterResponseDto
import com.security.presentation.dto.auth.UserInfoResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Authentication", description = "API de autenticação")
interface AuthResource {
    @Operation(
        summary = "Register",
        description = "Registra um novo usuário",
    )
    @ApiResponse(responseCode = "200", description = "Registro realizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário já existe")
    @PostMapping("/register")
    fun register(
        @RequestBody request: RegisterRequestDto,
    ): ResponseEntity<RegisterResponseDto>

    @Operation(
        summary = "Login",
        description = "Autentica um usuário e retorna um token JWT",
    )
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequestDto,
    ): ResponseEntity<LoginResponseDto>

    @Operation(
        summary = "Refresh Token",
        description = "Atualiza o token de acesso usando o refresh token",
    )
    @ApiResponse(responseCode = "200", description = "Token atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Refresh token inválido ou expirado")
    @PostMapping("/refresh")
    fun refreshToken(
        @RequestBody request: RefreshTokenRequestDto,
    ): ResponseEntity<RefreshTokenResponseDto>

    @Operation(
        summary = "Logout",
        description = "Realiza logout do usuário e invalida o token",
    )
    @ApiResponse(responseCode = "200", description = "Logout realizado com sucesso")
    @PostMapping("/logout")
    fun logout(
        @RequestHeader("Authorization") token: String,
    ): ResponseEntity<Unit>

    @Operation(
        summary = "Get User Info",
        description = "Obtém informações do usuário autenticado",
    )
    @ApiResponse(responseCode = "200", description = "Informações do usuário obtidas com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    @GetMapping("/me")
    fun getUserInfo(): ResponseEntity<UserInfoResponseDto>
}

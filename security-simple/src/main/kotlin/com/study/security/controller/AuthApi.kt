package com.study.security.controller

import com.study.security.dto.LoginRequest
import com.study.security.dto.LoginResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints de autenticação")
interface AuthApi {
    @Operation(summary = "Realiza o login na aplicação")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
        ],
    )
    @PostMapping("/login", consumes = ["application/json"], produces = ["application/json"])
    fun login(
        @RequestBody request: LoginRequest,
    ): ResponseEntity<LoginResponse>

    @Operation(
        summary = "Realiza logout na aplicação",
        description = "Encerra a sessão do usuário e invalida o token",
        security = [SecurityRequirement(name = "bearerAuth")],
    )
    @PostMapping("/logout")
    fun logout(): ResponseEntity<Map<String, String>>
}

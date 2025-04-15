package com.study.security.controller

import com.study.security.dto.LoginRequest
import com.study.security.dto.LoginResponse
import com.study.security.service.TokenService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(private val tokenService: TokenService) : AuthApi {
    private val validUsername = "admin"
    private val validPassword = "admin123"

    override fun login(request: LoginRequest): ResponseEntity<LoginResponse> =
        if (request.username == validUsername && request.password == validPassword) {
            val token = "dummy-token-${System.currentTimeMillis()}"
            tokenService.addValidToken(token)
            ResponseEntity.ok(
                LoginResponse(
                    token = token,
                    message = "Login realizado com sucesso",
                ),
            )
        } else {
            ResponseEntity.status(401).body(
                LoginResponse(
                    token = "",
                    message = "Credenciais inválidas",
                ),
            )
        }

    override fun logout(): ResponseEntity<Map<String, String>> {
        val auth = SecurityContextHolder.getContext().authentication
        val token = (auth.details as? String)?.substring(7) // Remove "Bearer " prefix
        token?.let { tokenService.invalidateToken(it) }
        SecurityContextHolder.clearContext()
        return ResponseEntity.ok(mapOf("message" to "Logout realizado com sucesso"))
    }
}

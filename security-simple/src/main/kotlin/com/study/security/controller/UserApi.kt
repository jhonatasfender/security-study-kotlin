package com.study.security.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Tag(name = "User", description = "APIs relacionadas as informações do usuário")
@RequestMapping("/api/user")
interface UserApi {
    @Operation(
        summary = "Obtém informações do usuário autenticado",
        description = "Retorna as informações do usuário atualmente autenticado",
        security = [SecurityRequirement(name = "bearerAuth")],
    )
    @GetMapping("/me")
    fun getCurrentUser(): ResponseEntity<Map<String, Any>>
}

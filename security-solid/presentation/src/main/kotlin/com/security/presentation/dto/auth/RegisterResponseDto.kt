package com.security.presentation.dto.auth

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Resposta de registro de usuário")
data class RegisterResponseDto(
    @Schema(description = "ID do usuário", example = "00000000-0000-0000-0000-000000000000")
    val id: String,
    @Schema(description = "Nome de usuário", example = "admin")
    val username: String,
    @Schema(description = "Roles do usuário", example = "[\"ADMIN\"]")
    val roles: List<String>,
) 
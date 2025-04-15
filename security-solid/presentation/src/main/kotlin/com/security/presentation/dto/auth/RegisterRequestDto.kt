package com.security.presentation.dto.auth

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Request para registro de usuário")
data class RegisterRequestDto @JsonCreator constructor(
    @JsonProperty("username")
    @Schema(description = "Nome de usuário", example = "admin")
    val username: String,
    @JsonProperty("password")
    @Schema(description = "Senha do usuário", example = "admin123")
    val password: String,
    @JsonProperty("roles")
    @Schema(description = "Roles do usuário", example = "[\"ADMIN\"]")
    val roles: List<String>,
) 
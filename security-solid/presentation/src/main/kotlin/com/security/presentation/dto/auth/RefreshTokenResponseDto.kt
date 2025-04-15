package com.security.presentation.dto.auth

import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant

@Schema(description = "Resposta de atualização de token")
data class RefreshTokenResponseDto(
    @Schema(description = "Novo token, JWT para autenticação", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    val accessToken: String,
    @Schema(description = "Novo refresh token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    val refreshToken: String,
    @Schema(description = "Data de expiração do token", example = "2024-03-20T12:00:00Z")
    val expiresAt: Instant,
) 

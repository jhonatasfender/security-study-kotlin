package com.security.presentation.dto.auth

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Request para atualização de token")
data class RefreshTokenRequestDto @JsonCreator constructor(
    @JsonProperty("refreshToken")
    @Schema(description = "Refresh token para atualização", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    val refreshToken: String,
) 

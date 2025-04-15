package com.security.presentation.dto.product

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.Instant

@Schema(description = "Resposta com informações do produto")
data class ProductResponseDto(
    @Schema(description = "ID do produto", example = "00000000-0000-0000-0000-000000000000")
    val id: String,
    @Schema(description = "Nome do produto", example = "Smartphone")
    val name: String,
    @Schema(description = "Descrição do produto", example = "Smartphone de última geração")
    val description: String,
    @Schema(description = "Preço do produto", example = "1999.99")
    val price: BigDecimal,
    @Schema(description = "ID do usuário dono do produto", example = "00000000-0000-0000-0000-000000000000")
    val userId: String,
    @Schema(description = "Data de criação do produto", example = "2024-03-20T12:00:00Z")
    val createdAt: Instant,
    @Schema(description = "Data de atualização do produto", example = "2024-03-20T12:00:00Z")
    val updatedAt: Instant,
) 
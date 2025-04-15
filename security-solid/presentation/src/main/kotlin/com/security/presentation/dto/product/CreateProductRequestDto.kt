package com.security.presentation.dto.product

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

@Schema(description = "Request para criação de produto")
data class CreateProductRequestDto @JsonCreator constructor(
    @JsonProperty("name")
    @Schema(description = "Nome do produto", example = "Smartphone")
    val name: String,
    @JsonProperty("description")
    @Schema(description = "Descrição do produto", example = "Smartphone de última geração")
    val description: String,
    @JsonProperty("price")
    @Schema(description = "Preço do produto", example = "1999.99")
    val price: BigDecimal,
) 
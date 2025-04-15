package com.security.presentation.resource.product

import com.security.presentation.dto.product.CreateProductRequestDto
import com.security.presentation.dto.product.ProductResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Products", description = "API de produtos")
interface ProductResource {
    @Operation(
        summary = "Create Product",
        description = "Cria um novo produto",
    )
    @ApiResponse(responseCode = "200", description = "Produto criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    fun create(
        @RequestBody request: CreateProductRequestDto,
    ): ResponseEntity<ProductResponseDto>

    @Operation(
        summary = "Get Product",
        description = "Obtém um produto pelo ID",
    )
    @ApiResponse(responseCode = "200", description = "Produto obtido com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: String,
    ): ResponseEntity<ProductResponseDto>

    @Operation(
        summary = "List Products",
        description = "Lista todos os produtos do usuário autenticado",
    )
    @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso")
    @GetMapping
    fun list(): ResponseEntity<List<ProductResponseDto>>

    @Operation(
        summary = "Delete Product",
        description = "Deleta um produto pelo ID",
    )
    @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: String,
    ): ResponseEntity<Unit>
} 
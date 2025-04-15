package com.study.security.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping

@Tag(name = "Hello Controller", description = "Endpoints de teste")
interface HelloApi {
    @Operation(summary = "Retorna uma mensagem de boas-vindas")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Mensagem retornada com sucesso"),
            ApiResponse(responseCode = "401", description = "Não autorizado"),
            ApiResponse(responseCode = "403", description = "Acesso negado"),
        ],
    )
    @GetMapping("/hello")
    fun hello(): String
}

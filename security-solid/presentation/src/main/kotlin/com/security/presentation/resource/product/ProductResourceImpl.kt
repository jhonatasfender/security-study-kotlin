package com.security.presentation.resource.product

import com.security.core.domain.auth.UserId
import com.security.core.domain.product.ProductId
import com.security.presentation.dto.product.CreateProductRequestDto
import com.security.presentation.dto.product.ProductResponseDto
import com.security.usecases.UseCaseExecutor
import com.security.usecases.product.*
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ProductResourceImpl(
    private val useCaseExecutor: UseCaseExecutor,
    private val createProductUseCase: CreateProductUseCase,
    private val getProductUseCase: GetProductUseCase,
    private val listProductsUseCase: ListProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
) : ProductResource {
    override fun create(request: CreateProductRequestDto): ResponseEntity<ProductResponseDto> {
        val authentication = SecurityContextHolder.getContext().authentication
            ?: throw RuntimeException("User isn't authenticated")

        val response = useCaseExecutor.execute(
            useCase = createProductUseCase,
            requestDto = request,
            requestConverter = {
                CreateProductRequest(
                    name = it.name,
                    description = it.description,
                    price = it.price,
                    userId = UserId(authentication.name),
                )
            },
            responseConverter = {
                ProductResponseDto(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    price = it.price,
                    userId = it.userId,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt,
                )
            },
        )
        return ResponseEntity.ok(response)
    }

    override fun getById(id: String): ResponseEntity<ProductResponseDto> {
        val response = useCaseExecutor.execute(
            useCase = getProductUseCase,
            requestDto = id,
            requestConverter = { GetProductRequest(ProductId(it)) },
            responseConverter = {
                ProductResponseDto(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    price = it.price,
                    userId = it.userId,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt,
                )
            },
        )
        return ResponseEntity.ok(response)
    }

    override fun list(): ResponseEntity<List<ProductResponseDto>> {
        val authentication = SecurityContextHolder.getContext().authentication
            ?: throw RuntimeException("User isn't authenticated")

        val response = useCaseExecutor.execute(
            useCase = listProductsUseCase,
            requestDto = authentication.name,
            requestConverter = { ListProductsRequest(UserId(it)) },
            responseConverter = { response ->
                response.products.map {
                    ProductResponseDto(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        price = it.price,
                        userId = it.userId,
                        createdAt = it.createdAt,
                        updatedAt = it.updatedAt,
                    )
                }
            },
        )
        return ResponseEntity.ok(response)
    }

    override fun delete(id: String): ResponseEntity<Unit> {
        useCaseExecutor.execute(
            useCase = deleteProductUseCase,
            requestDto = id,
            requestConverter = { DeleteProductRequest(ProductId(it)) },
            responseConverter = { Unit },
        )
        return ResponseEntity.ok().build()
    }
} 
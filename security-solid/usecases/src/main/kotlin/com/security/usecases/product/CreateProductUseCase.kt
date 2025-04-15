package com.security.usecases.product

import com.security.core.domain.auth.UserId
import com.security.core.domain.product.*
import com.security.core.ports.product.ProductRepository
import com.security.usecases.ports.UseCase
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

class CreateProductUseCase(
    private val productRepository: ProductRepository,
) : UseCase<CreateProductRequest, CreateProductResponse> {
    override fun execute(request: CreateProductRequest): CreateProductResponse {
        val product =
            Product(
                id = ProductId(UUID.randomUUID().toString()),
                name = ProductName(request.name),
                description = ProductDescription(request.description),
                price = ProductPrice(request.price),
                userId = request.userId,
            )

        val savedProduct = productRepository.save(product)

        return CreateProductResponse(
            id = savedProduct.id.value,
            name = savedProduct.name.value,
            description = savedProduct.description.value,
            price = savedProduct.price.value,
            userId = savedProduct.userId.value,
            createdAt = savedProduct.createdAt,
            updatedAt = savedProduct.updatedAt,
        )
    }
}

data class CreateProductRequest(
    val name: String,
    val description: String,
    val price: BigDecimal,
    val userId: UserId,
)

data class CreateProductResponse(
    val id: String,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val userId: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)

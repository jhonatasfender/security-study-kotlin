package com.security.usecases.product

import com.security.core.domain.product.ProductId
import com.security.core.ports.product.ProductRepository
import com.security.usecases.ports.UseCase
import java.math.BigDecimal
import java.time.Instant

class GetProductUseCase(
    private val productRepository: ProductRepository,
) : UseCase<GetProductRequest, GetProductResponse> {
    override fun execute(request: GetProductRequest): GetProductResponse {
        val product = productRepository.findById(request.id)
            ?: throw RuntimeException("Product not found")

        return GetProductResponse(
            id = product.id.value,
            name = product.name.value,
            description = product.description.value,
            price = product.price.value,
            userId = product.userId.value,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt,
        )
    }
}

data class GetProductRequest(
    val id: ProductId,
)

data class GetProductResponse(
    val id: String,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val userId: String,
    val createdAt: Instant,
    val updatedAt: Instant,
) 
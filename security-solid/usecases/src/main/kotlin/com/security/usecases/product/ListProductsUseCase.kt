package com.security.usecases.product

import com.security.core.domain.auth.UserId
import com.security.core.ports.product.ProductRepository
import com.security.usecases.ports.UseCase
import java.math.BigDecimal
import java.time.Instant

class ListProductsUseCase(
    private val productRepository: ProductRepository,
) : UseCase<ListProductsRequest, ListProductsResponse> {
    override fun execute(request: ListProductsRequest): ListProductsResponse {
        val products = productRepository.findAllByUserId(request.userId)

        return ListProductsResponse(
            products = products.map {
                ProductDto(
                    id = it.id.value,
                    name = it.name.value,
                    description = it.description.value,
                    price = it.price.value,
                    userId = it.userId.value,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt,
                )
            }
        )
    }
}

data class ListProductsRequest(
    val userId: UserId,
)

data class ListProductsResponse(
    val products: List<ProductDto>,
)

data class ProductDto(
    val id: String,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val userId: String,
    val createdAt: Instant,
    val updatedAt: Instant,
) 
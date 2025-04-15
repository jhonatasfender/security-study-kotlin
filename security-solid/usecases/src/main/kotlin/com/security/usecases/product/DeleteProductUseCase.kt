package com.security.usecases.product

import com.security.core.domain.product.ProductId
import com.security.core.ports.product.ProductRepository
import com.security.usecases.ports.UseCase

class DeleteProductUseCase(
    private val productRepository: ProductRepository,
) : UseCase<DeleteProductRequest, Unit> {
    override fun execute(request: DeleteProductRequest) {
        if (!productRepository.existsById(request.id)) {
            throw RuntimeException("Product not found")
        }
        productRepository.delete(request.id)
    }
}

data class DeleteProductRequest(
    val id: ProductId,
) 
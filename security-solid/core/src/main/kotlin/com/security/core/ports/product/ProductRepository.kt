package com.security.core.ports.product

import com.security.core.domain.product.Product
import com.security.core.domain.product.ProductId
import com.security.core.domain.auth.UserId

interface ProductRepository {
    fun findById(id: ProductId): Product?
    fun findAllByUserId(userId: UserId): List<Product>
    fun save(product: Product): Product
    fun delete(id: ProductId)
    fun existsById(id: ProductId): Boolean
} 
package com.security.core.domain.product

import com.security.core.domain.auth.UserId
import java.math.BigDecimal
import java.time.Instant

data class Product(
    val id: ProductId,
    val name: ProductName,
    val description: ProductDescription,
    val price: ProductPrice,
    val userId: UserId,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now(),
)

data class ProductId(
    val value: String,
)

data class ProductName(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "Product name cannot be blank" }
        require(value.length <= 100) { "Product name cannot exceed 100 characters" }
    }
}

data class ProductDescription(
    val value: String,
) {
    init {
        require(value.length <= 500) { "Product description cannot exceed 500 characters" }
    }
}

data class ProductPrice(
    val value: BigDecimal,
) {
    init {
        require(value >= BigDecimal.ZERO) { "Product price cannot be negative" }
    }
}

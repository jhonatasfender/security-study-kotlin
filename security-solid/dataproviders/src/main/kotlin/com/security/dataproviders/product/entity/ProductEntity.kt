package com.security.dataproviders.product.entity

import com.security.core.domain.auth.UserId
import com.security.core.domain.product.*
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.Instant

@Entity
@Table(name = "products")
data class ProductEntity(
    @Id
    @Column(name = "id", nullable = false)
    val id: String,
    @Column(name = "name", nullable = false, length = 100)
    val name: String,
    @Column(name = "description", nullable = false, length = 500)
    val description: String,
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    val price: BigDecimal,
    @Column(name = "user_id", nullable = false)
    val userId: String,
    @Column(name = "created_at", nullable = false)
    val createdAt: Instant = Instant.now(),
    @Column(name = "updated_at", nullable = false)
    val updatedAt: Instant = Instant.now(),
) {
    fun toDomain(): Product =
        Product(
            id = ProductId(id),
            name = ProductName(name),
            description = ProductDescription(description),
            price = ProductPrice(price),
            userId = UserId(userId),
            createdAt = createdAt,
            updatedAt = updatedAt,
        )

    companion object {
        fun fromDomain(product: Product): ProductEntity =
            ProductEntity(
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

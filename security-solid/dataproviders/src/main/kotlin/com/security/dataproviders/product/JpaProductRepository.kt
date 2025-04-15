package com.security.dataproviders.product

import com.security.core.domain.product.Product
import com.security.core.domain.product.ProductId
import com.security.core.domain.auth.UserId
import com.security.core.ports.product.ProductRepository
import com.security.dataproviders.product.entity.ProductEntity
import com.security.dataproviders.product.repository.ProductJpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class JpaProductRepository(
    private val productJpaRepository: ProductJpaRepository,
) : ProductRepository {
    override fun findById(id: ProductId): Product? =
        productJpaRepository.findById(id.value)
            .map { it.toDomain() }
            .orElse(null)

    override fun findAllByUserId(userId: UserId): List<Product> =
        productJpaRepository.findAllByUserId(userId.value)
            .map { it.toDomain() }

    override fun save(product: Product): Product =
        productJpaRepository.save(ProductEntity.fromDomain(product))
            .toDomain()

    override fun delete(id: ProductId) {
        productJpaRepository.deleteById(id.value)
    }

    override fun existsById(id: ProductId): Boolean =
        productJpaRepository.existsById(id.value)
}
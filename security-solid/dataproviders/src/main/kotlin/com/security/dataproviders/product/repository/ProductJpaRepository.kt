package com.security.dataproviders.product.repository

import com.security.dataproviders.product.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductJpaRepository : JpaRepository<ProductEntity, String> {
    fun findAllByUserId(userId: String): List<ProductEntity>
} 
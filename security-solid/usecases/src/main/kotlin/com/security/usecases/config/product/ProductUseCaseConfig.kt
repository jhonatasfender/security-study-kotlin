package com.security.usecases.config.product

import com.security.core.ports.product.ProductRepository
import com.security.usecases.config.UseCaseConfigModule
import com.security.usecases.product.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProductUseCaseConfig : UseCaseConfigModule {
    @Bean
    fun createProductUseCase(productRepository: ProductRepository): CreateProductUseCase = CreateProductUseCase(productRepository)

    @Bean
    fun getProductUseCase(productRepository: ProductRepository): GetProductUseCase = GetProductUseCase(productRepository)

    @Bean
    fun listProductsUseCase(productRepository: ProductRepository): ListProductsUseCase = ListProductsUseCase(productRepository)

    @Bean
    fun deleteProductUseCase(productRepository: ProductRepository): DeleteProductUseCase = DeleteProductUseCase(productRepository)

    override fun configure() {
        // Configurações específicas do módulo de produtos
    }
} 
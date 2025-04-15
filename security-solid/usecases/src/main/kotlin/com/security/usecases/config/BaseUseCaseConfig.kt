package com.security.usecases.config

import com.security.usecases.UseCaseExecutor
import com.security.usecases.config.auth.AuthUseCaseConfig
import com.security.usecases.config.product.ProductUseCaseConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    AuthUseCaseConfig::class,
    ProductUseCaseConfig::class,
)
class BaseUseCaseConfig(
    private val modules: List<UseCaseConfigModule>,
) {
    @Bean
    fun useCaseExecutor(): UseCaseExecutor = UseCaseExecutor()

    init {
        modules.forEach { it.configure() }
    }
}

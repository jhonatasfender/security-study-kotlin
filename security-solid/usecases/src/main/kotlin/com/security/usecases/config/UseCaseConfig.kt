package com.security.usecases.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(BaseUseCaseConfig::class)
class UseCaseConfig

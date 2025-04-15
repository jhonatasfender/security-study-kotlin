package com.security.presentation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(
    basePackages = [
        "com.security.presentation",
        "com.security.usecases",
        "com.security.dataproviders",
    ],
)
@EntityScan("com.security.dataproviders.**.entity")
@EnableJpaRepositories("com.security.dataproviders.**.repository")
class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<Application>(*args)
        }
    }
}

package com.study.security.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController : UserApi {
    override fun getCurrentUser(): ResponseEntity<Map<String, Any>> {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication

        val userInfo =
            mapOf(
                "username" to authentication.name,
                "authorities" to authentication.authorities.map { it.authority },
                "isAuthenticated" to authentication.isAuthenticated,
                "details" to authentication.details,
            )

        return ResponseEntity.ok(userInfo)
    }
}

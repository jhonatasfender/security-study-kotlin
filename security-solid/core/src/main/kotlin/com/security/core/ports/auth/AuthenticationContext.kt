package com.security.core.ports.auth

interface AuthenticationContext {
    val remoteAddress: String
    val sessionId: String?
    val userAgent: String?
    fun buildDetails(): AuthenticationDetails
} 
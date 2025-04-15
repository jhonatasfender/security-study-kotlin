package com.security.core.ports.auth

interface AuthenticationDetails {
    val remoteAddress: String
    val sessionId: String?
} 
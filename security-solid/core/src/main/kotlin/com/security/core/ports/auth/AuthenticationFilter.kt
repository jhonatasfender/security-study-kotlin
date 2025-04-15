package com.security.core.ports.auth

interface AuthenticationFilter {
    fun doFilter(
        request: AuthenticationRequest,
        response: AuthenticationResponse,
    )
}

data class AuthenticationRequest(
    val headers: Map<String, String>,
    val attributes: Map<String, Any>,
    val context: AuthenticationContext,
)

class AuthenticationResponse(
    var status: Int = 200,
    var message: String? = null,
)

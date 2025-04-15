package com.security.dataproviders.auth

import com.security.core.ports.auth.AuthenticationContext
import com.security.core.ports.auth.AuthenticationDetails
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component

@Component
class SpringAuthenticationDetails(
    private val request: HttpServletRequest,
) : AuthenticationContext {
    override val remoteAddress: String
        get() = request.remoteAddr

    override val sessionId: String?
        get() = request.requestedSessionId

    override val userAgent: String?
        get() = request.getHeader("User-Agent")

    override fun buildDetails(): AuthenticationDetails =
        object : AuthenticationDetails {
            override val remoteAddress: String = this@SpringAuthenticationDetails.remoteAddress
            override val sessionId: String? = this@SpringAuthenticationDetails.sessionId
        }
}

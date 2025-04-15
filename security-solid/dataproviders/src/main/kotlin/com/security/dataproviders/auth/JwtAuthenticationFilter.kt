package com.security.dataproviders.auth

import com.security.core.ports.auth.AuthProvider
import com.security.core.ports.auth.AuthenticationFilter
import com.security.core.ports.auth.AuthenticationRequest
import com.security.core.ports.auth.AuthenticationResponse
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val userDetailsService: UserDetailsService,
    private val authProvider: AuthProvider,
) : OncePerRequestFilter(),
    AuthenticationFilter {
    private val publicPaths =
        listOf(
            "/api/auth/login",
            "/api/auth/register",
            "/swagger-ui/",
            "/v3/api-docs/",
            "/actuator/",
            "/error",
        )

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val path = request.servletPath
        if (publicPaths.any { path.startsWith(it) }) {
            filterChain.doFilter(request, response)
            return
        }

        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(401, "Missing or invalid Authorization header")
            return
        }

        val jwt = authHeader.substring(7)
        if (!authProvider.validateToken(jwt)) {
            response.sendError(401, "Invalid or expired token")
            return
        }

        val username =
            authProvider.extractUsername(jwt)
                ?: throw RuntimeException("Invalid token: no username found")

        val userDetails = userDetailsService.loadUserByUsername(username)
        val authToken =
            UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.authorities,
            )
        authToken.details = request.remoteAddr
        SecurityContextHolder.getContext().authentication = authToken

        filterChain.doFilter(request, response)
    }

    override fun doFilter(
        request: AuthenticationRequest,
        response: AuthenticationResponse,
    ) {
        try {
            val authHeader = request.headers["Authorization"]
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.status = 401
                response.message = "Missing or invalid Authorization header"
                return
            }

            val jwt = authHeader.substring(7)
            if (!authProvider.validateToken(jwt)) {
                response.status = 401
                response.message = "Invalid or expired token"
                return
            }

            val username =
                authProvider.extractUsername(jwt)
                    ?: throw RuntimeException("Invalid token: no username found")

            val userDetails = userDetailsService.loadUserByUsername(username)
            val authToken =
                UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities,
                )
            authToken.details = request.context.remoteAddress
            SecurityContextHolder.getContext().authentication = authToken

            response.status = 200
        } catch (e: Exception) {
            response.status = 401
            response.message = "Authentication failed: ${e.message}"
        }
    }
}

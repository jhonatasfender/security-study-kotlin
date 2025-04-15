package com.security.dataproviders.auth

import com.security.core.ports.auth.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SpringSecurityContext : SecurityContext {
    override fun clearContext() {
        SecurityContextHolder.clearContext()
    }
}

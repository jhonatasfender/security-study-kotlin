package com.study.security.service

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class TokenService {
    private val validTokens = ConcurrentHashMap<String, Boolean>()

    fun isValidToken(token: String): Boolean = validTokens[token] ?: false

    fun invalidateToken(token: String) {
        validTokens.remove(token)
    }

    fun addValidToken(token: String) {
        validTokens[token] = true
    }
}

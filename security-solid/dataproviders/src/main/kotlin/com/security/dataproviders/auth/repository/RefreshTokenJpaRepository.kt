package com.security.dataproviders.auth.repository

import com.security.dataproviders.auth.entity.RefreshTokenEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface RefreshTokenJpaRepository : JpaRepository<RefreshTokenEntity, String> {
    fun findByToken(token: String): RefreshTokenEntity?

    fun findByUserId(userId: String): List<RefreshTokenEntity>

    fun deleteByUserId(userId: String)

    @Query("SELECT t FROM RefreshTokenEntity t WHERE t.token = ?1 AND t.revoked = false AND t.expiresAt > ?2")
    fun findValidToken(
        token: String,
        now: Instant = Instant.now(),
    ): RefreshTokenEntity?
}

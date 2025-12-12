package com.ttlabz.pombo.chat.infra.database.repositories

import com.ttlabz.pombo.chat.infra.database.entities.ChatParticipantEntity
import com.ttlabz.pombo.domain.type.UserId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ChatParticipantRepository : JpaRepository<ChatParticipantEntity, UserId> {
    fun findByUserIdIn(userIds: Set<UserId>): Set<ChatParticipantEntity>

    @Query("""
        SELECT p
        FROM ChatParticipantEntity p
        WHERE LOWER(p.username) = :query OR LOWER(p.email) = :query 
    """)
    fun findByEmailOrUsername(query: String): ChatParticipantEntity?
}
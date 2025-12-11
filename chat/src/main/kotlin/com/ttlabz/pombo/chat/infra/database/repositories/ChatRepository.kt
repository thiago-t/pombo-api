package com.ttlabz.pombo.chat.infra.database.repositories

import com.ttlabz.pombo.chat.infra.database.entities.ChatEntity
import com.ttlabz.pombo.domain.type.ChatId
import com.ttlabz.pombo.domain.type.UserId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ChatRepository : JpaRepository<ChatEntity, ChatId> {
    @Query(
        """
        SELECT c
        FROM ChatEntity c
        LEFT JOIN FETCH c.participants
        LEFT JOIN FETCH c.creator
        WHERE c.id = :id
        AND EXISTS (
            SELECT 1
            FROM c.participants p
            WHERE p.userId = :userId
        )
    """)
    fun findChatById(id: ChatId, userId: UserId): ChatEntity?

    // Query 1: Query all chats by a user
    // Query 2: Query all the participants of chat A
    // Query 3: Query the creator of that chat A
    // Query 4: Query all the participants of chat B
    // Query 5: Query the creator of that chat B
    // Query 6: Query all the participants of chat C
    // Query 7: Query the creator of that chat C
    @Query("""
        SELECT c
        FROM ChatEntity c
        LEFT JOIN FETCH c.participants
        LEFT JOIN FETCH c.creator
        WHERE EXISTS (
            SELECT 1
            FROM c.participants p
            WHERE p.userId = :userId
        )
    """)
    fun findAllByUserId(userId: UserId): List<ChatEntity>
}
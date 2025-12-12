package com.ttlabz.pombo.chat.service

import com.ttlabz.pombo.chat.domain.exception.ChatParticipantNotFoundException
import com.ttlabz.pombo.chat.domain.exception.InvalidChatSizeException
import com.ttlabz.pombo.chat.domain.models.Chat
import com.ttlabz.pombo.chat.infra.database.entities.ChatEntity
import com.ttlabz.pombo.chat.infra.database.mappers.toChat
import com.ttlabz.pombo.chat.infra.database.repositories.ChatParticipantRepository
import com.ttlabz.pombo.chat.infra.database.repositories.ChatRepository
import com.ttlabz.pombo.domain.type.UserId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatService(
    private val chatRepository: ChatRepository,
    private val chatParticipantRepository: ChatParticipantRepository,
) {

    @Transactional
    fun createChat(
        creatorId: UserId,
        otherUserIds: Set<UserId>,
    ): Chat {
        val otherParticipants = chatParticipantRepository.findByUserIdIn(
            userIds = otherUserIds
        )

        val allParticipants = (otherParticipants + creatorId)
        if (allParticipants.size < 2) {
            throw InvalidChatSizeException()
        }

        val creator = chatParticipantRepository.findByIdOrNull(creatorId)
            ?: throw ChatParticipantNotFoundException(creatorId)

        return chatRepository.save(
            ChatEntity(
                creator = creator,
                participants = setOf(creator) + otherParticipants,
            )
        ).toChat(lastMessage = null)
    }

}
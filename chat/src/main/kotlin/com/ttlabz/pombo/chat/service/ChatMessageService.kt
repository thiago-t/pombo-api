package com.ttlabz.pombo.chat.service

import com.ttlabz.pombo.chat.api.dto.ChatMessageDto
import com.ttlabz.pombo.chat.api.mappers.toChatMessageDto
import com.ttlabz.pombo.chat.domain.exception.ChatNotFoundException
import com.ttlabz.pombo.chat.domain.exception.ChatParticipantNotFoundException
import com.ttlabz.pombo.chat.domain.exception.MessageNotFoundException
import com.ttlabz.pombo.chat.domain.models.ChatMessage
import com.ttlabz.pombo.chat.infra.database.entities.ChatMessageEntity
import com.ttlabz.pombo.chat.infra.database.mappers.toChatMessage
import com.ttlabz.pombo.chat.infra.database.repositories.ChatMessageRepository
import com.ttlabz.pombo.chat.infra.database.repositories.ChatParticipantRepository
import com.ttlabz.pombo.chat.infra.database.repositories.ChatRepository
import com.ttlabz.pombo.domain.exception.ForbiddenException
import com.ttlabz.pombo.domain.type.ChatId
import com.ttlabz.pombo.domain.type.ChatMessageId
import com.ttlabz.pombo.domain.type.UserId
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class ChatMessageService(
    private val chatRepository: ChatRepository,
    private val chatMessageRepository: ChatMessageRepository,
    private val chatParticipantRepository: ChatParticipantRepository,
) {

    @Transactional
    fun sendMessage(
        chatId: ChatId,
        senderId: UserId,
        content: String,
        messageId: ChatMessageId? = null
    ): ChatMessage {
        val chat = chatRepository.findChatById(chatId, senderId)
            ?: throw ChatNotFoundException()
        val sender = chatParticipantRepository.findByIdOrNull(senderId)
            ?: throw ChatParticipantNotFoundException(senderId)
        val savedMessage = chatMessageRepository.save(
            ChatMessageEntity(
                id = messageId,
                content = content.trim(),
                chatId = chatId,
                chat = chat,
                sender = sender
            )
        )

        return savedMessage.toChatMessage()
    }

    @Transactional
    fun deleteMessage(
        messageId: ChatMessageId,
        requestUserId: UserId
    ) {
        val message = chatMessageRepository.findByIdOrNull(messageId)
            ?: throw MessageNotFoundException(messageId)

        if (message.sender.userId != requestUserId) {
            throw ForbiddenException()
        }

        chatMessageRepository.delete(message)
    }

}
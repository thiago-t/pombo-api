package com.ttlabz.pombo.chat.service

import com.ttlabz.pombo.chat.domain.event.MessageDeletedEvent
import com.ttlabz.pombo.chat.domain.exception.ChatNotFoundException
import com.ttlabz.pombo.chat.domain.exception.ChatParticipantNotFoundException
import com.ttlabz.pombo.chat.domain.exception.MessageNotFoundException
import com.ttlabz.pombo.chat.domain.models.ChatMessage
import com.ttlabz.pombo.chat.infra.database.entities.ChatMessageEntity
import com.ttlabz.pombo.chat.infra.database.mappers.toChatMessage
import com.ttlabz.pombo.chat.infra.database.repositories.ChatMessageRepository
import com.ttlabz.pombo.chat.infra.database.repositories.ChatParticipantRepository
import com.ttlabz.pombo.chat.infra.database.repositories.ChatRepository
import com.ttlabz.pombo.domain.events.chat.ChatEvent
import com.ttlabz.pombo.domain.exception.ForbiddenException
import com.ttlabz.pombo.domain.type.ChatId
import com.ttlabz.pombo.domain.type.ChatMessageId
import com.ttlabz.pombo.domain.type.UserId
import com.ttlabz.pombo.infra.message_queue.EventPublisher
import org.springframework.cache.annotation.CacheEvict
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ChatMessageService(
    private val chatRepository: ChatRepository,
    private val chatMessageRepository: ChatMessageRepository,
    private val chatParticipantRepository: ChatParticipantRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val eventPublisher: EventPublisher,
    private val messageCacheEvictionHelper: MessageCacheEvictionHelper,
) {

    @Transactional
    @CacheEvict(
        value = ["messages"],
        key = "#chatId",
    )
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
        val savedMessage = chatMessageRepository.saveAndFlush(
            ChatMessageEntity(
                id = messageId ?: UUID.randomUUID(),
                content = content.trim(),
                chatId = chatId,
                chat = chat,
                sender = sender
            )
        )

        eventPublisher.publish(
            event = ChatEvent.NewMessage(
                senderId = sender.userId,
                senderUsername = sender.username,
                recipientIds = chat.participants.map { it.userId }.toSet(),
                chatId = chatId,
                message = savedMessage.content,
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

        applicationEventPublisher.publishEvent(
            MessageDeletedEvent(
                chatId = message.chatId,
                messageId = messageId,
            )
        )

        messageCacheEvictionHelper.evictMessagesCache(message.chatId)
    }

}
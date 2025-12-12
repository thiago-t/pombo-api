package com.ttlabz.pombo.chat.api.mappers

import com.ttlabz.pombo.chat.api.dto.ChatDto
import com.ttlabz.pombo.chat.api.dto.ChatMessageDto
import com.ttlabz.pombo.chat.api.dto.ChatParticipantDto
import com.ttlabz.pombo.chat.domain.models.Chat
import com.ttlabz.pombo.chat.domain.models.ChatMessage
import com.ttlabz.pombo.chat.domain.models.ChatParticipant

fun Chat.toChatDto(): ChatDto {
    return ChatDto(
        id = id,
        participants = participants.map {
            it.toChatParticipantDto()
        },
        lastActivityAt = lastActivityAt,
        lastMessage = lastMessage?.toChatMessageDto(),
        creator = creator.toChatParticipantDto()
    )
}

fun ChatMessage.toChatMessageDto(): ChatMessageDto {
    return ChatMessageDto(
        id = id,
        chatId = chatId,
        content = content,
        createdAt = createdAt,
        senderId = sender.userId
    )
}

fun ChatParticipant.toChatParticipantDto(): ChatParticipantDto {
    return ChatParticipantDto(
        userId = userId,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl
    )
}
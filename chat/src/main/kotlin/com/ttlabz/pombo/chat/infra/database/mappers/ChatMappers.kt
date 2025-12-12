package com.ttlabz.pombo.chat.infra.database.mappers

import com.ttlabz.pombo.chat.domain.models.Chat
import com.ttlabz.pombo.chat.domain.models.ChatMessage
import com.ttlabz.pombo.chat.domain.models.ChatParticipant
import com.ttlabz.pombo.chat.infra.database.entities.ChatEntity
import com.ttlabz.pombo.chat.infra.database.entities.ChatParticipantEntity

fun ChatEntity.toChat(lastMessage: ChatMessage? = null): Chat {
    return Chat(
        id = id!!,
        participants = participants.map {
            it.toChatParticipant()
        }.toSet(),
        creator = creator.toChatParticipant(),
        lastActivityAt = lastMessage?.createdAt ?: createdAt,
        lastMessage = lastMessage,
        createdAt = createdAt
    )
}

fun ChatParticipantEntity.toChatParticipant(): ChatParticipant {
    return ChatParticipant(
        userId = userId,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl
    )
}

fun ChatParticipant.toChatParticipantEntity(): ChatParticipantEntity {
    return ChatParticipantEntity(
        userId = userId,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl
    )
}
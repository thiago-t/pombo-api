package com.ttlabz.pombo.chat.api.dto

import com.ttlabz.pombo.domain.type.ChatId
import com.ttlabz.pombo.domain.type.ChatMessageId
import com.ttlabz.pombo.domain.type.UserId
import java.time.Instant

data class ChatMessageDto(
    val id: ChatMessageId,
    val chatId: ChatId,
    val content: String,
    val createdAt: Instant,
    val senderId: UserId
)

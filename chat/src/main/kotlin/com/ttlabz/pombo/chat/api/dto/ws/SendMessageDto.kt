package com.ttlabz.pombo.chat.api.dto.ws

import com.ttlabz.pombo.domain.type.ChatId
import com.ttlabz.pombo.domain.type.ChatMessageId

data class SendMessageDto(
    val chatId: ChatId,
    val content: String,
    val messageId: ChatMessageId? = null,
)

package com.ttlabz.pombo.chat.domain.event

import com.ttlabz.pombo.domain.type.ChatId
import com.ttlabz.pombo.domain.type.ChatMessageId

data class MessageDeletedEvent(
    val chatId: ChatId,
    val messageId: ChatMessageId,
) {
}
package com.ttlabz.pombo.chat.domain.event

import com.ttlabz.pombo.domain.type.ChatId
import com.ttlabz.pombo.domain.type.UserId

data class ChatCreatedEvent(
    val chatId: ChatId,
    val participantIds: List<UserId>,
)

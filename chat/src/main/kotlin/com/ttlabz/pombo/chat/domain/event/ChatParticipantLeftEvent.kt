package com.ttlabz.pombo.chat.domain.event

import com.ttlabz.pombo.domain.type.ChatId
import com.ttlabz.pombo.domain.type.UserId

data class ChatParticipantLeftEvent(
    val chatId: ChatId,
    val userId: UserId
)

package com.ttlabz.pombo.chat.api.dto.ws

import com.ttlabz.pombo.domain.type.ChatId

data class ChatParticipantsChangedDto(
    val chatId: ChatId
)

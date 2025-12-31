package com.ttlabz.pombo.chat.domain.exception

import com.ttlabz.pombo.domain.type.ChatMessageId

class MessageNotFoundException(
    private val id: ChatMessageId
) : RuntimeException(
    "Message with ID $id not found"
)
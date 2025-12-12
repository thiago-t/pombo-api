package com.ttlabz.pombo.chat.domain.exception

import com.ttlabz.pombo.domain.type.UserId

class ChatParticipantNotFoundException(
    private val id: UserId,
) : RuntimeException(
    "The chat participant with the ID $id was not found"
)
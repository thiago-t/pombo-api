package com.ttlabz.pombo.chat.domain.models

import com.ttlabz.pombo.domain.type.UserId

data class ChatParticipant(
    val userId: UserId,
    val username: String,
    val email: String,
    val profilePictureUrl: String?
)
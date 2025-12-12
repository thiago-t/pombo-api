package com.ttlabz.pombo.chat.api.dto

import com.ttlabz.pombo.domain.type.UserId

data class ChatParticipantDto(
    val userId: UserId,
    val username: String,
    val email: String,
    val profilePictureUrl: String?
)

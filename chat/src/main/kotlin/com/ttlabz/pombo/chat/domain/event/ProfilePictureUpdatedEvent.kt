package com.ttlabz.pombo.chat.domain.event

import com.ttlabz.pombo.domain.type.UserId

data class ProfilePictureUpdatedEvent(
    val userId: UserId,
    val newUrl: String?
)

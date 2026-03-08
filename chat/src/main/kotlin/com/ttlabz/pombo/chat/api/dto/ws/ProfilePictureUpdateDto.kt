package com.ttlabz.pombo.chat.api.dto.ws

import com.ttlabz.pombo.domain.type.UserId

data class ProfilePictureUpdateDto(
    val userId: UserId,
    val newUrl: String?
)

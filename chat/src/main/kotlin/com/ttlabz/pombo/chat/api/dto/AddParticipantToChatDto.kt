package com.ttlabz.pombo.chat.api.dto

import com.ttlabz.pombo.domain.type.UserId
import jakarta.validation.constraints.Size

data class AddParticipantToChatDto(
    @field:Size(min = 1)
    val userIds: List<UserId>
)

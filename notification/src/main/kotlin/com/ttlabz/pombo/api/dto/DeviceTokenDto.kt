package com.ttlabz.com.ttlabz.pombo.api.dto

import com.ttlabz.pombo.domain.type.UserId
import java.time.Instant

data class DeviceTokenDto(
    val userId: UserId,
    val token: String,
    val createdAt: Instant
)

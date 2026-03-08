package com.ttlabz.pombo.api.mappers

import com.ttlabz.pombo.api.dto.DeviceTokenDto
import com.ttlabz.pombo.api.dto.PlatformDto
import com.ttlabz.pombo.domain.model.DeviceToken

fun DeviceToken.toDeviceTokenDto(): DeviceTokenDto {
    return DeviceTokenDto(
        userId = userId,
        token = token,
        createdAt = createdAt
    )
}

fun PlatformDto.toPlatform(): DeviceToken.Platform {
    return when (this) {
        PlatformDto.ANDROID -> DeviceToken.Platform.ANDROID
        PlatformDto.IOS -> DeviceToken.Platform.IOS
    }
}
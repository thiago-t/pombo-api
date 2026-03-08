package com.ttlabz.com.ttlabz.pombo.infra.mappers

import com.ttlabz.com.ttlabz.pombo.infra.database.DeviceTokenEntity
import com.ttlabz.pombo.domain.model.DeviceToken

fun DeviceTokenEntity.toDeviceToken(): DeviceToken {
    return DeviceToken(
        userId = userId,
        token = token,
        platform = platform.toPlatform(),
        createdAt = createdAt,
        id = id
    )
}
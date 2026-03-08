package com.ttlabz.com.ttlabz.pombo.api.controllers

import com.ttlabz.com.ttlabz.pombo.api.dto.DeviceTokenDto
import com.ttlabz.com.ttlabz.pombo.api.dto.RegisterDeviceRequest
import com.ttlabz.com.ttlabz.pombo.api.mappers.toDeviceTokenDto
import com.ttlabz.com.ttlabz.pombo.api.mappers.toPlatform
import com.ttlabz.com.ttlabz.pombo.service.PushNotificationService
import com.ttlabz.pombo.api.util.requestUserId
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/notification")
class DeviceTokenController(
    private val pushNotificationService: PushNotificationService
) {

    @PostMapping("/register")
    fun registerDeviceToken(
        @Valid @RequestBody body: RegisterDeviceRequest
    ): DeviceTokenDto {
        return pushNotificationService.registerDevice(
            userId = requestUserId,
            token = body.token,
            platform = body.platformDto.toPlatform()
        ).toDeviceTokenDto()
    }

    @DeleteMapping("/{token}")
    fun unregisterDeviceToken(
        @PathVariable("token") token: String
    ) {
        pushNotificationService.unregisterDevice(token)
    }

}
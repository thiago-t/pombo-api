package com.ttlabz.pombo.chat.api.mappers

import com.ttlabz.pombo.chat.api.dto.PictureUploadResponse
import com.ttlabz.pombo.chat.domain.models.ProfilePictureUploadCredentials

fun ProfilePictureUploadCredentials.toResponse(): PictureUploadResponse {
    return PictureUploadResponse(
        uploadUrl = uploadUrl,
        publicUrl = publicUrl,
        header = header,
        expiresAt = expiresAt
    )
}
package com.ttlabz.pombo.chat.api.controllers

import com.ttlabz.pombo.api.util.requestUserId
import com.ttlabz.pombo.chat.api.dto.ChatParticipantDto
import com.ttlabz.pombo.chat.api.dto.ConfirmProfilePictureRequest
import com.ttlabz.pombo.chat.api.dto.PictureUploadResponse
import com.ttlabz.pombo.chat.api.mappers.toChatParticipantDto
import com.ttlabz.pombo.chat.api.mappers.toResponse
import com.ttlabz.pombo.chat.service.ChatParticipantService
import com.ttlabz.pombo.chat.service.ProfilePictureService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/participants")
class ChatParticipantController(
    private val chatParticipantService: ChatParticipantService,
    private val profilePictureService: ProfilePictureService,
) {

    @GetMapping
    fun getChatParticipantByUsernameOrEmail(
        @RequestParam(required = false) query: String?,
    ): ChatParticipantDto {
        val participant = if (query == null) {
            chatParticipantService.findChatParticipantById(requestUserId)
        } else {
            chatParticipantService.findChatParticipantByEmailOrUsername(query)
        }

        return participant?.toChatParticipantDto()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping("/profile-picture-upload")
    fun getProfilePictureUploadUrl(
        @RequestParam mimeType: String,
    ): PictureUploadResponse {
        return profilePictureService.generateUploadCredentials(
            userId = requestUserId,
            mimeType = mimeType
        ).toResponse()
    }

    @PostMapping("/confirm-profile-picture")
    fun confirmProfilePictureUploadUrl(
        @Valid @RequestBody body: ConfirmProfilePictureRequest,
    ) {
        return profilePictureService.confirmProfilePictureUpload(
            userId = requestUserId,
            publicUrl = body.publicUrl,
        )
    }

    @DeleteMapping("/profile-picture")
    fun deleteProfilePicture() {
        profilePictureService.deleteProfilePicture(
            userId = requestUserId
        )
    }

}
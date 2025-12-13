package com.ttlabz.pombo.chat.api.controllers

import com.ttlabz.pombo.api.util.requestUserId
import com.ttlabz.pombo.chat.api.dto.AddParticipantToChatDto
import com.ttlabz.pombo.chat.api.dto.ChatDto
import com.ttlabz.pombo.chat.api.dto.CreateChatRequest
import com.ttlabz.pombo.chat.api.mappers.toChatDto
import com.ttlabz.pombo.chat.service.ChatService
import com.ttlabz.pombo.domain.type.ChatId
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/chat")
class ChatController(
    private val chatService: ChatService,
) {

    @PostMapping
    fun createChat(
        @Valid @RequestBody body: CreateChatRequest,
    ): ChatDto {
        return chatService.createChat(
            creatorId = requestUserId,
            otherUserIds = body.otherUserIds.toSet(),
        ).toChatDto()
    }

    @PostMapping("/{chatId}/add")
    fun addChatParticipants(
        @PathVariable chatId: ChatId,
        @Valid @RequestBody body: AddParticipantToChatDto
    ): ChatDto {
        return chatService.addParticipantsToChat(
            requestUserId = requestUserId,
            chatId = chatId,
            userIds = body.userIds.toSet()
        ).toChatDto()
    }

    @DeleteMapping("/{chatId}/leave")
    fun leaveChat(
        @PathVariable chatId: ChatId
    ) {
        chatService.removeParticipantFromChat(
            chatId = chatId,
            userId = requestUserId,
        )
    }

}
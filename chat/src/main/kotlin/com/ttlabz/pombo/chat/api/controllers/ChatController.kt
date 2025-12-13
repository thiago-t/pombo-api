package com.ttlabz.pombo.chat.api.controllers

import com.ttlabz.pombo.api.util.requestUserId
import com.ttlabz.pombo.chat.api.dto.ChatDto
import com.ttlabz.pombo.chat.api.dto.CreateChatRequest
import com.ttlabz.pombo.chat.api.mappers.toChatDto
import com.ttlabz.pombo.chat.service.ChatService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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

}
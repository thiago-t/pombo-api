package com.ttlabz.pombo.chat.api.controllers

import com.ttlabz.pombo.api.util.requestUserId
import com.ttlabz.pombo.chat.api.dto.AddParticipantToChatDto
import com.ttlabz.pombo.chat.api.dto.ChatDto
import com.ttlabz.pombo.chat.api.dto.ChatMessageDto
import com.ttlabz.pombo.chat.api.dto.CreateChatRequest
import com.ttlabz.pombo.chat.api.mappers.toChatDto
import com.ttlabz.pombo.chat.service.ChatMessageService
import com.ttlabz.pombo.chat.service.ChatService
import com.ttlabz.pombo.domain.type.ChatId
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/api/v1/chat")
class ChatController(
    private val chatService: ChatService,
) {

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
    }

    @GetMapping("/{chatId}/messages")
    fun getMessagesForChat(
        @PathVariable("chatId") chatId: ChatId,
        @RequestParam("before", required = false) before: Instant? = null,
        @RequestParam("pageSize", required = false) pageSize: Int = DEFAULT_PAGE_SIZE
    ): List<ChatMessageDto> {
        return chatService.getChatMessages(
            chatId = chatId,
            before = before,
            pageSize = pageSize
        )
    }

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
package com.ttlabz.pombo.domain.events.chat

import com.ttlabz.pombo.domain.events.ChirpEvent
import com.ttlabz.pombo.domain.events.user.UserEventConstants
import com.ttlabz.pombo.domain.type.ChatId
import com.ttlabz.pombo.domain.type.UserId
import java.time.Instant
import java.util.*

sealed class ChatEvent(
    override val eventId: String = UUID.randomUUID().toString(),
    override val exchange: String = ChatEventConstants.CHAT_EXCHANGE,
    override val occurredAt: Instant = Instant.now(),
): ChirpEvent {

    data class NewMessage(
        val senderId: UserId,
        val senderUsername: String,
        val recipientIds: Set<UserId>,
        val chatId: ChatId,
        val message: String,
        override val eventKey: String = ChatEventConstants.CHAT_NEW_MESSAGE
    ): ChatEvent(), ChirpEvent

}
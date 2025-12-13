package com.ttlabz.pombo.chat.infra.messaging

import com.ttlabz.pombo.chat.domain.models.ChatParticipant
import com.ttlabz.pombo.chat.service.ChatParticipantService
import com.ttlabz.pombo.domain.events.user.UserEvent
import com.ttlabz.pombo.infra.message_queue.MessageQueues
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class ChatUserEventListener(
    private val chatParticipantService: ChatParticipantService
) {

    @RabbitListener(queues = [MessageQueues.CHAT_USER_EVENTS_QUEUE])
    fun handleUserEvent(event: UserEvent) {
        when (event) {
            is UserEvent.Verified -> {
                chatParticipantService.createChatParticipant(
                    chatParticipant = ChatParticipant(
                        userId = event.userId,
                        username = event.username,
                        email = event.email,
                        profilePictureUrl = null
                    )
                )
            }

            else -> Unit
        }
    }

}
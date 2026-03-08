package com.ttlabz.com.ttlabz.pombo.infra.message_queue

import com.ttlabz.com.ttlabz.pombo.service.PushNotificationService
import com.ttlabz.pombo.domain.events.chat.ChatEvent
import com.ttlabz.pombo.infra.message_queue.MessageQueues
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class NotificationChatEventListener(
    private val pushNotificationService: PushNotificationService
) {

    @RabbitListener(queues = [MessageQueues.NOTIFICATION_CHAT_EVENTS])
    fun handleUserEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.NewMessage -> {
                pushNotificationService.sendNewMessageNotifications(
                    recipientUserIds = event.recipientIds.toList(),
                    senderUserId = event.senderId,
                    senderUserName = event.senderUsername,
                    message = event.message,
                    chatId = event.chatId
                )
            }

            else -> Unit
        }
    }
}
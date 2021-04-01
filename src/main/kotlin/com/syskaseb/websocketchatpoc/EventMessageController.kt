package com.syskaseb.websocketchatpoc

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

/*
@Controller
class EventMessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    fun get(eventMessage: EventMessage): String {
        return """{"user": "${eventMessage.user}", "message": "${eventMessage.message}"}"""
    }
}
*/

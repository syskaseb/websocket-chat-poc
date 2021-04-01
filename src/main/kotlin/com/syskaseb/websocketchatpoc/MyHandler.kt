package com.syskaseb.websocketchatpoc

import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class MyHandler : TextWebSocketHandler() {

    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("client ${session.id} connected")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("received message: ${message.payload}")
        session.sendMessage(TextMessage("Some important server message"))
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("client ${session.id} disconnected")
    }
}
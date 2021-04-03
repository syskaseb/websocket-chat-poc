package com.syskaseb.websocketchatpoc

import org.json.JSONObject
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class MyHandler : TextWebSocketHandler() {

    private var rooms = mutableListOf<Room>()
    private var cnt = 0

    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("client ${session.id} connected")
        cnt++
        if (cnt % 2 != 0) {
            rooms.add(Room())
        }
        rooms.last().sessions.add(session)
        session.sendMessage(
            TextMessage(
                JSONObject().put("server_message", "client joined room ${rooms.lastIndex}").toString()
            )
        )
        println("rooms count: ${rooms.count()}")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("received message: ${message.payload}")
        println(JSONObject(message.payload).getString("eventType"))
        val eventMessage = JSONObject(message.payload).getJSONObject("eventMessage")
        println("[rowIndex, colIndex]: [${eventMessage.getInt("rowIndex")}, ${eventMessage.getInt("colIndex")}]")
        session.sendMessage(
            TextMessage(
                JSONObject().put(
                    "server_message",
                    "Some important server message to the current session"
                ).toString()
            )
        )
        rooms.forEachIndexed { index, room ->
            room.sessions.forEach {
                it.sendMessage(
                    TextMessage(
                        JSONObject().put(
                            "server_message",
                            "Server message to room $index"
                        ).toString()
                    )
                )
            }
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("client ${session.id} disconnected")
    }

    private class Room(var sessions: MutableList<WebSocketSession> = mutableListOf())
}

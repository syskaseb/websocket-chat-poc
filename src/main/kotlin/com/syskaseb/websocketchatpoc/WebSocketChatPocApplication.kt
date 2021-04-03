package com.syskaseb.websocketchatpoc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebSocketChatPocApplication

fun main(args: Array<String>) {
    runApplication<WebSocketChatPocApplication>(*args)
}

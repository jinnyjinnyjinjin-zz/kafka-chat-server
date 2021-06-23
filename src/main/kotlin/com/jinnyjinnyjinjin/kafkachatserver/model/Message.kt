package com.jinnyjinnyjinjin.kafkachatserver.model

import java.time.LocalDateTime

data class Message(
    val sender: String,
    val content: String,
    var timestamp: String?
) {
    fun setTimestamp() {
        timestamp = LocalDateTime.now().toString()
    }
}
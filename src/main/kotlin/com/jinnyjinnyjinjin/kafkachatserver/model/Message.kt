package com.jinnyjinnyjinjin.kafkachatserver.model

import java.time.LocalTime

data class Message(
    val sender: String,
    val content: String,
    var timestamp: LocalTime?
) {
    fun setTimestamp() {
        timestamp = LocalTime.now()
    }
}
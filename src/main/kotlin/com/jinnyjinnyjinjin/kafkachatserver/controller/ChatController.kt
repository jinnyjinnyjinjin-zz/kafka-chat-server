package com.jinnyjinnyjinjin.kafkachatserver.controller

import com.jinnyjinnyjinjin.kafkachatserver.constants.KafkaConstants
import com.jinnyjinnyjinjin.kafkachatserver.model.Message
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ExecutionException

@RestController
@RequestMapping("/api/v1")
class ChatController(
    val kafkaTemplate: KafkaTemplate<String, Message>,
) {

    private val logger: Logger = LoggerFactory.getLogger(ChatController::class.java)

    @PostMapping(value = ["/send"], consumes = ["application/json"], produces = ["application/json"])
    fun sendMessage(
        @RequestBody message: Message,
    ) {
        logger.info("SEND")
        message.setTimestamp()
        try {
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message)
        } catch (e: InterruptedException) {
            throw RuntimeException()
        } catch (e: ExecutionException) {
            throw RuntimeException()
        }
    }
}
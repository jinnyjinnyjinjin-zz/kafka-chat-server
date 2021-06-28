package com.jinnyjinnyjinjin.kafkachatserver.controller

import com.jinnyjinnyjinjin.kafkachatserver.constants.KafkaConstants
import com.jinnyjinnyjinjin.kafkachatserver.model.Message
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ExecutionException

@RestController
@RequestMapping("/api/v1")
class ChatApi(
    val kafkaTemplate: KafkaTemplate<String, Message>,
) {

    private val logger: Logger = LoggerFactory.getLogger(ChatApi::class.java)

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

    @MessageMapping("/send")
    @SendTo("/topic/group")
    fun broadcastGroupMessage(@Payload message: Message): Message {
        return message
    }
}
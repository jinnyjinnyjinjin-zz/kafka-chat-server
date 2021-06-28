package com.jinnyjinnyjinjin.kafkachatserver

import com.jinnyjinnyjinjin.kafkachatserver.constants.KafkaConstants
import com.jinnyjinnyjinjin.kafkachatserver.model.Message
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class MessageListener(
    private val template: SimpMessagingTemplate
) {
    private val logger: Logger = LoggerFactory.getLogger(MessageListener::class.java)

    @KafkaListener(
        topics = [KafkaConstants.KAFKA_TOPIC]
    )
    fun listen(message: Message) {
        logger.debug("Sending via Kafka Listener")
        template.convertAndSend("/topic/group", message)
    }
}
package com.jinnyjinnyjinjin.kafkachatserver.config

import com.jinnyjinnyjinjin.kafkachatserver.constants.KafkaConstants
import com.jinnyjinnyjinjin.kafkachatserver.model.Message
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class ListenerConfiguration {

   @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Message> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Message>()
       factory.consumerFactory = consumerFactory()
       return factory
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Message> {
        return DefaultKafkaConsumerFactory(consumerConfigurations(), StringDeserializer(), JsonDeserializer(Message::class.java))
    }

    @Bean
    fun consumerConfigurations(): Map<String, Any> {
        return mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to KafkaConstants.KAFKA_BROKER,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest"
        )
    }
}
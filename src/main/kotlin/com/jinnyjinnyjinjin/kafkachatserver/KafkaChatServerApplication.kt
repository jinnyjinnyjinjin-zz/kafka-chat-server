package com.jinnyjinnyjinjin.kafkachatserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaChatServerApplication

fun main(args: Array<String>) {
	runApplication<KafkaChatServerApplication>(*args)
}

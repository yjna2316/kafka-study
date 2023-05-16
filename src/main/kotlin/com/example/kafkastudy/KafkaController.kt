package com.example.kafkastudy

import com.example.kafkastudy.producer.KafkaProducer
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class KafkaController(
    val kafkaProducer: KafkaProducer
) {

    @PostMapping("/api/produce")
    fun produce(
        @RequestBody request: RequestData,
    ) {
        kafkaProducer.sendMessage(request.topic, request.message)
    }
}

data class RequestData(
    val topic: String,
    val message: String
)

package com.example.kafkastudy.producer

import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class KafkaProducer(
    val kafkaTemplate: KafkaTemplate<String, String>
) {
    fun sendMessage(topic: String, message: String) {
        val future: CompletableFuture<SendResult<String, String>> = kafkaTemplate.send(topic, message)
        future.whenComplete { result, throwable ->
            when (throwable == null) {
                true -> handleSuccess(result)
                false -> handleError(result.producerRecord, throwable?.message)
            }
        }
    }

    private fun handleSuccess(result: SendResult<String, String>) {
        println("Success producing message to topic: ${result.producerRecord}")
        println("Success producing message to recordMetadata: ${result.recordMetadata.partition()}")
    }

    private fun handleError(
        producerRecord: ProducerRecord<String, String>,
        errorMessage: String?
    ) {
        println("Error while producing message to topic: $producerRecord, error: $errorMessage")
    }
}

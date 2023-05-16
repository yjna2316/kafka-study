package com.example.kafkastudy.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaTopicConfig {

    @Bean
    fun topicBanking() =
        KafkaAdmin.NewTopics(
            TopicBuilder.name("banking-register-account")
                .partitions(3)
                .replicas(2)
                .build(),
            TopicBuilder.name("banking-unregister-account")
                .build()
        )
}

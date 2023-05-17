package com.example.kafkastudy.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaTopicConfig {
    // kafkaAdmin 빈을 등록해줘야 어플레케이션 실행시 토픽이 자동으로 생성된다.
    // 토픽이 이미 존재하는 경우, 파티션 수 변경은 늘리는것만 가능, 감소는 수정 안됨. 레플리카 수는 증감 모두 수정안  5
    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs: MutableMap<String, Any> = HashMap()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:29092,localhost:39093,localhost:49092" // 카프카 브로커 주소 설정
        return KafkaAdmin(configs)
    }

    @Bean
    fun topicBanking(): KafkaAdmin.NewTopics {
        return KafkaAdmin.NewTopics(
            TopicBuilder.name("banking-register-account")
                .partitions(20)
                .replicas(5)
                .build(),
            TopicBuilder.name("banking-unregister-account")
                .build()
        )
    }
}

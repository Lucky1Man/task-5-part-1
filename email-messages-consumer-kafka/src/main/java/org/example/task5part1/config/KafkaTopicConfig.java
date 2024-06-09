package org.example.task5part1.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.simpleEmail}")
    private String simpleEmailTopic;

    @Bean
    public NewTopic paymentReceivedTopic() {
        return new NewTopic(simpleEmailTopic, 2, (short) 1);
    }

}

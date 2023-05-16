package com.global.automotivebackend.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic gpsTopic() {
        return TopicBuilder.name("gpsTopic")
                .build();
    }

    @Bean
    public NewTopic companyTopic() {
        return TopicBuilder.name("companyTopic")
                .build();
    }

    @Bean
    public NewTopic vehicleTopic() {
        return TopicBuilder.name("vehicleTopic")
                .build();
    }

    @Bean
    public NewTopic deviceTopic() {
        return TopicBuilder.name("deviceTopic")
                .build();
    }
}

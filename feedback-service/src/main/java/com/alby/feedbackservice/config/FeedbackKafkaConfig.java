package com.alby.feedbackservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class FeedbackKafkaConfig {

    @Bean
    public NewTopic feedbackEvents() {
        return TopicBuilder.name("feedback.events")
                .partitions(1)
                .build();
    }
}

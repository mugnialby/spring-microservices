package com.alby.userservice.config;

import lombok.NoArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@NoArgsConstructor
public class UserKafkaConfig {

    @Value("${kafka.topic.user.events}")
    private String userEvents;

    @Value("${kafka.topic.user.events.partition}")
    private int partitionCount;

    @Bean
    public NewTopic userEvents() {
        return TopicBuilder.name(userEvents)
            .partitions(partitionCount)
            .build();
    }
}

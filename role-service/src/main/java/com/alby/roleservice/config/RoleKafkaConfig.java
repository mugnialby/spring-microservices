package com.alby.roleservice.config;

import lombok.NoArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@NoArgsConstructor
public class RoleKafkaConfig {

    @Value("${kafka.topic.role.events}")
    private String roleEvents;

    @Value("${kafka.topic.role.events.partition}")
    private int partitionCount;

    @Bean
    public NewTopic roleEvents() {
        return TopicBuilder.name(roleEvents)
            .partitions(partitionCount)
            .build();
    }
}

package com.alby.springmicroservices.userservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class UserEventConfig {

    @Bean
    public NewTopic userEvents() {
        return TopicBuilder.name("user.events")
            // .partitions(3)
            .build();
    }
}

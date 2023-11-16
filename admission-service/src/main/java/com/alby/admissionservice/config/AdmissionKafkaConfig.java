package com.alby.admissionservice.config;

import lombok.NoArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@NoArgsConstructor
public class AdmissionKafkaConfig {

    @Value("${kafka.topic.admission.events}")
    private String admissionEvents;

    @Value("${kafka.topic.admission.events.partition}")
    private int partitionCount;

    @Bean
    public NewTopic admissionEvents() {
        return TopicBuilder.name(admissionEvents)
            .partitions(partitionCount)
            .build();
    }
}

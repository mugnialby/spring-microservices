package com.alby.patientservice.config;

import lombok.NoArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@NoArgsConstructor
public class PatientKafkaConfig {

    @Value("${kafka.topic.patient.events}")
    private String patientEvents;

    @Value("${kafka.topic.patient.events.partition}")
    private int partitionCount;

    @Bean
    public NewTopic patientEvents() {
        return TopicBuilder.name(patientEvents)
            .partitions(partitionCount)
            .build();
    }
}

package com.alby.springmicroservices.userservice.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserProducer {

    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Value("${kafka.topic.user.events}")
    private String userEventsTopic;

    public <T> void sendMessage(T user) {
        Message<T> message = MessageBuilder
            .withPayload(user)
            .setHeader(
                KafkaHeaders.TOPIC, 
                "user.event"
            ).build();

        log.info(String.format("Message sent %s", message));
        kafkaTemplate.send(userEventsTopic, message.toString());
    }

    public <T> void sendMessage(Page<T> users) {
        Message<Page<T>> message = MessageBuilder
            .withPayload(users)
            .setHeader(
                KafkaHeaders.TOPIC, 
                "user.event"
            ).build();

        log.info(String.format("Message sent %s", message));
        kafkaTemplate.send(message);
    }
}
package com.alby.springmicroservices.messagetesterservice.producer;

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
public class MessageTesterProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    public <T> void sendMessage(T data) {
        Message<T> message = MessageBuilder
            .withPayload(data)
            .setHeader(
                KafkaHeaders.TOPIC, 
                "user.events"
            ).build();

        log.info(String.format("Message sent %s", message));
        kafkaTemplate.send(message);
    }
}
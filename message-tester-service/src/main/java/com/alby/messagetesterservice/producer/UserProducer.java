package com.alby.messagetesterservice.producer;

import com.alby.messagetesterservice.util.MessageTesterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void produce(String jsonData, String messageKey) {
        Message<String> message = MessageBuilder
                .withPayload(jsonData)
                .setHeader(KafkaHeaders.TOPIC,"user.events")
                .setHeader(KafkaHeaders.CORRELATION_ID, "tes123")
                .setHeader(KafkaHeaders.KEY, messageKey)
                .build();

        log.info(String.format("Message sent %s", message));
        kafkaTemplate.send(message);
    }
}

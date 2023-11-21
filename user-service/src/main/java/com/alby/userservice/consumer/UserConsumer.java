package com.alby.userservice.consumer;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public class UserConsumer {

    @KafkaListener(
            topics = "user.events"
    )
    public void consume(
            @Header(KafkaHeaders.CORRELATION_ID) String correlationId,
            @Header(KafkaHeaders.KEY) String messageKey,
            String message
    ) {
        log.info(String.format("Event Type received -> %s | Message key received -> %s | Message received -> %s",
                correlationId, messageKey, message));
//        Consumer<String> handler = userEventHandlers.getOrDefault(eventType, this::handleUnknownEvent);
//        handler.accept(message);
    }
}

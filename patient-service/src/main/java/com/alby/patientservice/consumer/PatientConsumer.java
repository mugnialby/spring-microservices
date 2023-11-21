package com.alby.patientservice.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatientConsumer {

    @KafkaListener(
            topics = "patient.events"
    )
    public void consume(
            @Header(KafkaHeaders.CORRELATION_ID) String correlationId,
            @Header(KafkaHeaders.KEY) String messageKey,
            String message
    ) {
        log.info(String.format("Correlation ID received -> %s | Message Key received -> %s | Message received -> %s",
                correlationId, messageKey, message));
//        Consumer<String> handler = userEventHandlers.getOrDefault(eventType, this::handleUnknownEvent);
//        handler.accept(message);
    }
}

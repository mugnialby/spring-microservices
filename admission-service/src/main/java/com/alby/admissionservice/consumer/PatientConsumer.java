package com.alby.admissionservice.consumer;

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
            @Header(KafkaHeaders.KEY) String messageKey
    ) {
        log.info(String.format("Event Type received -> %s | Message received -> %s", correlationId, messageKey));
//        Consumer<String> handler = userEventHandlers.getOrDefault(eventType, this::handleUnknownEvent);
//        handler.accept(message);
    }

    private void handleAddEvent(String message) {

    }

    private void handleDeleteEvent(String message) {
        // Logic to handle 'delete' operation
        System.out.println("Handling 'delete' event: " + message);
    }

    private void handleUpdateEvent(String message) {
        // Logic to handle 'update' operation
        System.out.println("Handling 'update' event: " + message);
    }

    private void handleGetByIdEvent(String message) {
        // Logic to handle 'getById' operation
        System.out.println("Handling 'getById' event: " + message);
    }

    private void handleGetAllEvent(String message) {
        // Logic to handle 'getAll' operation
        System.out.println("Handling 'getAll' event: " + message);
    }

    private void handleUnknownEvent(String message) {
        // Logic to handle unknown or unexpected events
        System.out.println("Handling unknown event: " + message);
    }
}

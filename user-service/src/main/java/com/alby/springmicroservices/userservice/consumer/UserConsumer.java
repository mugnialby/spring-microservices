package com.alby.springmicroservices.userservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.alby.springmicroservices.userservice.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserConsumer {
    
    private UserService userService;

    @KafkaListener(
        topics = "user-event",
        groupId = "springServices"
    )
    public void consume(@Header("eventType") String eventType, String message) {
        log.info(String.format("Event Type Received : %s | Message Received : %s", eventType, message));
    }
}
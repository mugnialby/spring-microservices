package com.alby.messagetesterservice.util;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@NoArgsConstructor
@Builder
public class MessageTesterUtil {

    @Value("${spring.microservices.sender.id}")
    private static String senderId;

    public static String getCorrelationId() {
        return senderId +
                "." +
                UUID.randomUUID() +
                "." +
                System.currentTimeMillis();
    }
}

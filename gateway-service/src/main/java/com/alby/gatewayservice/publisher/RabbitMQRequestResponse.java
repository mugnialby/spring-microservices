package com.alby.gatewayservice.publisher;

import com.alby.gatewayservice.configurations.RabbitMQConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RabbitMQRequestResponse {
    private final RabbitTemplate rabbitTemplate;

    public Map<String, Object> request(
            String routingKey,
            Map<String, Object> request
    ) {
        rabbitTemplate.setReplyTimeout(500);
        return (Map<String, Object>) rabbitTemplate.convertSendAndReceive(
                routingKey,
                request
        );
    }
}

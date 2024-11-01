package com.alby.userservice.subscriber;

import com.alby.userservice.configuration.RabbitMQConfiguration;
import com.alby.userservice.dto.response.UserResponse;
import com.alby.userservice.dto.response.WebResponse;
import com.alby.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserMessageSubscriber {
    private final UserService userService;

    private final RabbitTemplate rabbitTemplate;

    private final RabbitMQConfiguration rabbitMQConfiguration;

    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "authenticate.request.queue")
    public void receiveMessage(Message message) {
        String correlationId = message.getMessageProperties().getCorrelationId();

        try {
            Map<String, Object> request = objectMapper.readValue(message.getBody(), Map.class);
            String username = (String) request.get("username");
            String password = (String) request.get("password");

            WebResponse<UserResponse> response = userService.findByCredential(username, password);

            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setCorrelationId(correlationId);

            Message responseMessage = MessageBuilder.withBody(objectMapper.writeValueAsBytes(response))
                    .andProperties(messageProperties)
                    .build();

            rabbitTemplate.send(
                    rabbitMQConfiguration.getRabbitMQExchangeAuthenticate(),
                    rabbitMQConfiguration.getRabbitMQQueueAuthenticateResponse(),
                    responseMessage
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

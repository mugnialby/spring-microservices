package com.alby.userservice.subscriber;

import com.alby.userservice.configuration.RabbitMQConfiguration;
import com.alby.userservice.dto.request.login.LoginRequest;
import com.alby.userservice.dto.response.UserResponse;
import com.alby.userservice.dto.response.WebResponse;
import com.alby.userservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserMessageSubscriber {
    private final UserService userService;

    private final ObjectMapper objectMapper;

    private final Jackson2JsonMessageConverter messageConverter;

    @RabbitListener(queues = "authenticate.request.queue")
    public Map<String, Object> receiveAuthenticateMessage(Message message) {
        LoginRequest loginRequest = objectMapper.convertValue(
                messageConverter.fromMessage(message),
                LoginRequest.class
        );

        UserResponse userResponse = userService.findByCredential(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        if (userResponse == null) {
            return null;
        }

        return objectMapper.convertValue(userResponse, new TypeReference<>(){});
    }
}

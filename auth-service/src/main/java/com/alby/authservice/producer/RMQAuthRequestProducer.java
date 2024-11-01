package com.alby.authservice.producer;

import com.alby.authservice.configuration.rabbitmq.RabbitMQConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

//@Component
//@RequiredArgsConstructor
public class RMQAuthRequestProducer {

//    private final RabbitTemplate rabbitTemplate;
//
//    private final RabbitMQConfiguration rabbitMQConfiguration;
//
//    public Map<String, Object> sendAuthenticationRequest(
//            String exchange,
//            String routingKey,
//            Map<String, Object> messageRequest
//    ) {
//       String correlationId = UUID.randomUUID().toString();
//
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setCorrelationId(correlationId);
//        messageProperties.setReplyTo(rabbitMQConfiguration.getRabbitMQQueueAuthenticateResponse());
//        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
//
//        Jackson2JsonMessageConverter messageConverter = (Jackson2JsonMessageConverter) rabbitTemplate.getMessageConverter();
//        Message message = messageConverter.toMessage(messageRequest, messageProperties);
//
//        rabbitTemplate.setReplyTimeout(5000);
//        return (Map<String, Object>) rabbitTemplate.convertSendAndReceive(exchange, routingKey, message);
//    }
}

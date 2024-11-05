package com.alby.userservice.configuration;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RabbitMQConfiguration {
    @Value("${rabbitmq.queue.authenticate.request}")
    private String rabbitMQQueueAuthenticateRequest;

    @Value("${rabbitmq.queue.authenticate.response}")
    private String rabbitMQQueueAuthenticateResponse;

    @Value("${rabbitmq.exchange.authenticate}")
    private String rabbitMQExchangeAuthenticate;

    @Bean("authenticateRequestQueueBean")
    public Queue authenticateRequestQueue() {
        return new Queue(rabbitMQQueueAuthenticateRequest, false);
    }

    @Bean("authenticateResponseQueueBean")
    public Queue authenticateResponseQueue() {
        return new Queue(rabbitMQQueueAuthenticateResponse, false);
    }

    @Bean
    public DirectExchange authenticateExchange() {
        return new DirectExchange(rabbitMQExchangeAuthenticate);
    }

    @Bean
    public Binding requestBinding(
            @Qualifier("authenticateRequestQueueBean") Queue requestQueue,
            DirectExchange exchange
    ) {
        return BindingBuilder.bind(requestQueue)
                .to(exchange)
                .with(rabbitMQQueueAuthenticateRequest);
    }

    @Bean
    public Binding responseBinding(
            @Qualifier("authenticateResponseQueueBean") Queue responseQueue,
            DirectExchange exchange
    ) {
        return BindingBuilder.bind(responseQueue)
                .to(exchange)
                .with(rabbitMQQueueAuthenticateResponse);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

package com.alby.gatewayservice.configurations;

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
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Getter
@Configuration
public class RabbitMQConfiguration {
    @Value("${rabbitmq.queue.authorization.request}")
    private String authorizationRequestQueue;

    @Value("${rabbitmq.queue.authorization.response}")
    private String authorizationResponseQueue;

    @Value("${rabbitmq.exchange.authorization}")
    private String authorizationExchange;

    @Bean("authorizationRequestQueueBean")
    public Queue authorizationRequestQueue() {
        return new Queue(authorizationRequestQueue, false);
    }

    @Bean("authorizationResponseQueueBean")
    public Queue authorizationResponseQueue() {
        return new Queue(authorizationResponseQueue, false);
    }

    @Bean
    public DirectExchange authorizationExchange() {
        return new DirectExchange(authorizationExchange);
    }

    @Bean
    public Binding requestBinding(
            @Qualifier("authorizationRequestQueueBean") Queue requestQueue,
            DirectExchange exchange
    ) {
        return BindingBuilder.bind(requestQueue)
                .to(exchange)
                .with(authorizationRequestQueue);
    }

    @Bean
    public Binding responseBinding(
            @Qualifier("authorizationResponseQueueBean") Queue responseQueue,
            DirectExchange exchange
    ) {
        return BindingBuilder.bind(responseQueue)
                .to(exchange)
                .with(authorizationResponseQueue);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RetryTemplate getRetryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(1000);
        exponentialBackOffPolicy.setMultiplier(2.0);
        exponentialBackOffPolicy.setMaxInterval(10000);

        return retryTemplate;
    }
}

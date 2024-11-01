package com.alby.authservice.configuration.rabbitmq;


import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitMQConfiguration {

//    @Value("${rabbitmq.server.host}")
//    private String rabbitMQHost;
//
//    @Value("${rabbitmq.server.username}")
//    private String rabbitMQUsername;
//
//    @Value("${rabbitmq.server.password}")
//    private String rabbitMQPassword;
//
//    @Value("${rabbitmq.queue.authenticate.request}")
//    @Getter
//    private String rabbitMQQueueAuthenticateRequest;
//
//    @Value("${rabbitmq.queue.authenticate.response}")
//    @Getter
//    private String rabbitMQQueueAuthenticateResponse;
//
//    @Value("${rabbitmq.exchange.authenticate}")
//    @Getter
//    private String rabbitMQExchangeAuthenticate;
//
//    @Bean
//    public Queue authenticateRequestQueue() {
//        return new Queue(rabbitMQQueueAuthenticateRequest);
//    }
//
//    @Bean
//    public Queue authenticateResponseQueue() {
//        return new Queue(rabbitMQQueueAuthenticateResponse);
//    }
//
//    @Bean
//    public DirectExchange authenticateExchange() {
//        return new DirectExchange(rabbitMQExchangeAuthenticate);
//    }
//
//    @Bean
//    public Binding requestBinding(Queue requestQueue, DirectExchange exchange) {
//        return BindingBuilder.bind(requestQueue)
//                .to(exchange)
//                .with(rabbitMQQueueAuthenticateRequest);
//    }
//
//    @Bean
//    public Binding responseBinding(Queue responseQueue, DirectExchange exchange) {
//        return BindingBuilder.bind(responseQueue)
//                .to(exchange)
//                .with(rabbitMQQueueAuthenticateResponse);
//    }
}

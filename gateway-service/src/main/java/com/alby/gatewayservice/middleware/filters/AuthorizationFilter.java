package com.alby.gatewayservice.middleware.filters;

import com.alby.gatewayservice.configurations.RabbitMQConfiguration;
import com.alby.gatewayservice.publisher.RabbitMQRequestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthorizationFilter
        extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    private final ObjectMapper objectMapper;

    private final RabbitMQRequestResponse rabbitMQRequestResponse;

    private final RabbitMQConfiguration rabbitMQConfiguration;

    public AuthorizationFilter(
            ObjectMapper objectMapper,
            RabbitMQRequestResponse rabbitMQRequestResponse,
            RabbitMQConfiguration rabbitMQConfiguration
    ) {
        super(Config.class);
        this.objectMapper = objectMapper;
        this.rabbitMQRequestResponse = rabbitMQRequestResponse;
        this.rabbitMQConfiguration = rabbitMQConfiguration;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
            String authorizationToken = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);

            if (authorizationToken == null) {
                return exchange.getResponse()
                        .writeWith(Mono.just(onError(
                                exchange,
                                "Request unauthorized")
                        ));
            }

            Map<String, Object> authorizationRequest = new HashMap<>();
            authorizationRequest.put("authorizationToken", authorizationToken);

            Map<String, Object> authorizationCheck = rabbitMQRequestResponse.request(
                    rabbitMQConfiguration.getAuthorizationRequestQueue(),
                    authorizationRequest
            );

            if (authorizationCheck == null) {
                return exchange.getResponse()
                        .writeWith(Mono.just(onError(
                                exchange,
                                "Service not available")
                        ));
            }

            if (!((boolean) authorizationCheck.get("valid"))) {
                return exchange.getResponse()
                        .writeWith(Mono.just(onError(
                                exchange,
                                "Authorization token is not valid")
                        ));
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {

    }

    private DataBuffer onError(
            ServerWebExchange exchange,
            String message
    ) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> response = new HashMap<>();
        response.put("message", message);

        DataBuffer dataBuffer;
        try {
            dataBuffer = exchange.getResponse()
                    .bufferFactory()
                    .wrap(objectMapper.writeValueAsString(response)
                            .getBytes(StandardCharsets.UTF_8)
                    );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return dataBuffer;
    }
}
package com.alby.authservice.serviceimpl;

import com.alby.authservice.configuration.rabbitmq.RabbitMQConfiguration;
import com.alby.authservice.dto.request.LoginRequest;
import com.alby.authservice.dto.request.VerifyTokenRequest;
import com.alby.authservice.dto.response.WebResponse;
import com.alby.authservice.producer.RMQAuthRequestProducer;
import com.alby.authservice.service.AuthService;
import com.alby.authservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;

    private final RMQAuthRequestProducer authRequestProducer;

    private final RabbitMQConfiguration rabbitMQConfiguration;

    @Override
    public WebResponse<String> login(LoginRequest request) {
//        Map<String, Object> message = new HashMap<>();
//        message.put("username", request.getUsername());
//        message.put("password", request.getPassword());
//
//        Map<String, Object> response = authRequestProducer.sendAuthenticationRequest(
//                rabbitMQConfiguration.getRabbitMQExchangeAuthenticate(),
//                rabbitMQConfiguration.getRabbitMQQueueAuthenticateRequest(),
//                message
//        );
//
//        if (response == null) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//        }

        return WebResponse.<String> builder()
                .message("OK")
                .build();
    }

    @Override
    public WebResponse<String> verify(VerifyTokenRequest request) {
        if (!jwtService.validateToken(request.getToken())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return WebResponse.<String> builder()
                .message("OK")
                .build();
    }
}
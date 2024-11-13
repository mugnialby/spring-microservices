package com.alby.authservice.serviceimpl;

import com.alby.authservice.configuration.rabbitmq.RabbitMQConfiguration;
import com.alby.authservice.dto.request.LoginRequest;
import com.alby.authservice.dto.request.VerifyTokenRequest;
import com.alby.authservice.dto.response.LoginResponse;
import com.alby.authservice.dto.response.UserResponse;
import com.alby.authservice.dto.response.WebResponse;
import com.alby.authservice.producer.RabbitMQAuthRequestProducer;
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

    private final RabbitMQAuthRequestProducer authRequestProducer;

    private final RabbitMQConfiguration rabbitMQConfiguration;

    @Override
    public WebResponse<LoginResponse> login(LoginRequest request) {
        Map<String, Object> message = new HashMap<>();
        message.put("username", request.getUsername());
        message.put("password", request.getPassword());

        UserResponse userResponse = authRequestProducer.sendAuthenticationRequest(
                rabbitMQConfiguration.getRabbitMQQueueAuthenticateRequest(),
                message
        );

        if (userResponse == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtService.generateToken(userResponse.getUsername()))
                .build();

        return WebResponse.<LoginResponse>builder()
                .message("OK")
                .data(loginResponse)
                .build();
    }

    @Override
    public boolean verify(VerifyTokenRequest request) {
        return jwtService.validateToken(request.getAuthorizationToken());
    }
}
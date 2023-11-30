package com.alby.messagetesterservice.serviceimpl;

import com.alby.messagetesterservice.dto.LoginRequest;
import com.alby.messagetesterservice.dto.users.response.WebResponse;
import com.alby.messagetesterservice.producer.UserProducer;
import com.alby.messagetesterservice.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserProducer userProducer;

    private final ObjectMapper objectMapper;

    @Override
    public WebResponse<String> testLogin(LoginRequest request) {
        try {
            userProducer.produce(objectMapper.writeValueAsString(request), "loginRequest");
        } catch (Exception e) {

        }

        return WebResponse.<String> builder()
                .message("OK")
                .build();
    }
}

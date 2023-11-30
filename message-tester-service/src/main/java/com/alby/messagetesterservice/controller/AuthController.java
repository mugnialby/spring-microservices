package com.alby.messagetesterservice.controller;

import com.alby.messagetesterservice.dto.LoginRequest;
import com.alby.messagetesterservice.dto.users.response.WebResponse;
import com.alby.messagetesterservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> testLogin(@RequestBody LoginRequest request) {
        return authService.testLogin(request);
    }
}

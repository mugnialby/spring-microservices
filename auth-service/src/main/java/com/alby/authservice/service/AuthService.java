package com.alby.authservice.service;

import com.alby.authservice.dto.request.LoginRequest;
import com.alby.authservice.dto.request.VerifyTokenRequest;
import com.alby.authservice.dto.response.UserResponse;
import com.alby.authservice.dto.response.WebResponse;

public interface AuthService {

    WebResponse<String> login(LoginRequest loginRequest);

    WebResponse<String> verify(VerifyTokenRequest request);
}

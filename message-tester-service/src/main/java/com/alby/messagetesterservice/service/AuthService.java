package com.alby.messagetesterservice.service;

import com.alby.messagetesterservice.dto.LoginRequest;
import com.alby.messagetesterservice.dto.users.response.WebResponse;

public interface AuthService {

    WebResponse<String> testLogin(LoginRequest request);
}

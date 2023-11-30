package com.alby.authservice.serviceimpl;

import com.alby.authservice.dto.request.LoginRequest;
import com.alby.authservice.dto.request.VerifyTokenRequest;
import com.alby.authservice.dto.response.UserResponse;
import com.alby.authservice.dto.response.WebResponse;
import com.alby.authservice.service.AuthService;
import com.alby.authservice.service.JwtService;
import com.alby.authservice.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ValidationService validationService;

    private final JwtService jwtService;

    @Override
    public WebResponse<String> login(LoginRequest request) {
        validationService.validate(request);

//        WebResponse<UserResponse> userResponse =

//        if (null == userResponse) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }

        return WebResponse.<String> builder()
                .message("OK")
//                .data(jwtService.generateToken(userResponse
//                        .getData()
//                        .getUsername()))
                .build();
    }

    @Override
    public WebResponse<String> verify(VerifyTokenRequest request) {
        validationService.validate(request);

        if (!jwtService.validateToken(request.getToken())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return WebResponse.<String> builder()
                .message("OK")
                .build();
    }
}

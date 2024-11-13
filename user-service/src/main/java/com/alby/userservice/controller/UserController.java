package com.alby.userservice.controller;

import java.util.List;

import com.alby.userservice.dto.request.login.LoginRequest;
import com.alby.userservice.dto.request.users.*;
import com.alby.userservice.dto.response.UserResponse;
import com.alby.userservice.dto.response.WebResponse;
import com.alby.userservice.service.ValidationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.alby.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    private final ValidationService validationService;
    
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<UserResponse>> getAll(@ModelAttribute UserPagingRequest request) {
        validationService.validate(request);
        return userService.getAll(request);
    }
    
    @GetMapping(
        path = "/find",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> get(@ModelAttribute UserGetRequest request) {
        validationService.validate(request);
        return userService.get(request);
    }
    
    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> add(@RequestBody UserAddRequest request) {
        validationService.validate(request);
        return userService.add(request);
    }
    
    @PutMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> update(@RequestBody UserUpdateRequest request) {
        validationService.validate(request);
        return userService.update(request);
    }
    
    @DeleteMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@RequestBody UserDeleteRequest request) {
        validationService.validate(request);
        return userService.delete(request);
    }
}

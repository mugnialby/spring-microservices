package com.alby.springmicroservices.userservice.service;

import java.util.List;
import java.util.Optional;

import com.alby.springmicroservices.userservice.dto.request.AddUserRequest;
import com.alby.springmicroservices.userservice.dto.request.UpdateUserRequest;
import com.alby.springmicroservices.userservice.dto.response.UserResponse;
import com.alby.springmicroservices.userservice.dto.response.WebResponse;

public interface UserService {
    
    public WebResponse<List<UserResponse>> getAll(Optional<Integer> page, Optional<Integer> size);
    
    public WebResponse<UserResponse> get(Long userId);

    public void add(AddUserRequest request);

    public WebResponse<UserResponse> update(Long userId, UpdateUserRequest request);

    public void delete(Long userId);
}

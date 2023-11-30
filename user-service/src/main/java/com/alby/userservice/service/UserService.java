package com.alby.userservice.service;

import java.util.List;

import com.alby.userservice.dto.request.login.LoginRequest;
import com.alby.userservice.dto.request.users.*;
import com.alby.userservice.dto.response.UserResponse;
import com.alby.userservice.dto.response.WebResponse;

public interface UserService {

    WebResponse<List<UserResponse>> getAll(UserPagingRequest request);

    WebResponse<UserResponse> get(UserGetRequest request);

    WebResponse<UserResponse> findByCredential(String username, String password);

    WebResponse<UserResponse> add(UserAddRequest request);

    WebResponse<UserResponse> update(UserUpdateRequest request);

    WebResponse<String> delete(UserDeleteRequest request);
}

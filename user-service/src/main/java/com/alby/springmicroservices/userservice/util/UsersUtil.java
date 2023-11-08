package com.alby.springmicroservices.userservice.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.alby.springmicroservices.userservice.dto.request.AddUserRequest;
import com.alby.springmicroservices.userservice.dto.response.UserResponse;
import com.alby.springmicroservices.userservice.entity.User;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class UsersUtil {
    
    public UserResponse mapUserToUserResponse(User user) {
        return UserResponse
            .builder()
            .id(user.getId())
            .username(user.getUsername())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .status(user.getStatus())
            .build();
    }

    public User mapAddRequestToUsers(AddUserRequest request) {
        return User.builder()
            .username(request.getUserName())
            .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .build();
    }
}

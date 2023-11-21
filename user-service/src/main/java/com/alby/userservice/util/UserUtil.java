package com.alby.userservice.util;

import com.alby.userservice.entity.Users;
import com.alby.userservice.dto.request.UserAddRequest;
import com.alby.userservice.dto.response.UserResponse;

import com.alby.userservice.security.BCrypt;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;


@NoArgsConstructor
@Builder
public class UserUtil {

    @Value("${spring.microservices.sender.id}")
    private static String senderId;

    public static String getCorrelationId() {
        return senderId +
                "." +
                UUID.randomUUID() +
                "." +
                System.currentTimeMillis();
    }
    
    public static UserResponse mapUserToUserResponse(Users user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .status(user.getStatus())
                .managerId(user.getManagerId())
                .build();
    }

    public static Users mapAddRequestToUsers(UserAddRequest request) {
        return Users.builder()
                .username(request.getUsername())
                .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .managerId(request.getManagerId())
                .createdBy(request.getCreatedBy())
                .build();
    }
}

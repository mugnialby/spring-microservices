package com.alby.springmicroservices.messagetesterservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddUserRequest {
    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String email;
}

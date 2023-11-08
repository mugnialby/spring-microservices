package com.alby.springmicroservices.userservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddUserRequest {

    @NotBlank
    @Size(max = 64)
    private String userName;

    @NotBlank
    @Size(max = 128)
    private String password;

    @NotBlank
    @Size(max = 128)
    private String firstName;

    @NotBlank
    @Size(max = 128)
    private String lastName;

    @NotBlank
    @Size(max = 64)
    private String email;
}

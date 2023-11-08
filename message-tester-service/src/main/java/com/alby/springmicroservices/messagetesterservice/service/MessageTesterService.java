package com.alby.springmicroservices.messagetesterservice.service;

import org.springframework.http.ResponseEntity;

import com.alby.springmicroservices.messagetesterservice.dto.request.AddUserRequest;

public interface MessageTesterService {
    
    public ResponseEntity<String> publishAdd(AddUserRequest addUserRequest);
}

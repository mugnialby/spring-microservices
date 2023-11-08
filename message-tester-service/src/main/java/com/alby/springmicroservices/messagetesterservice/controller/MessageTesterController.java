package com.alby.springmicroservices.messagetesterservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alby.springmicroservices.messagetesterservice.dto.request.AddUserRequest;
import com.alby.springmicroservices.messagetesterservice.service.MessageTesterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(
    path = "/api/v1/message/"
)
@RequiredArgsConstructor
public class MessageTesterController {
    
    private MessageTesterService messageTesterService;

    @PostMapping(
        path = "add"
    )
    public ResponseEntity<String> sendAddRequest(@RequestBody AddUserRequest addUserRequest) {
        return messageTesterService.publishAdd(addUserRequest);
    }
}

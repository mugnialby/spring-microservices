package com.alby.messagetesterservice.controller;

import com.alby.messagetesterservice.dto.users.request.UserAddRequest;
import com.alby.messagetesterservice.service.MessageTesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tester/users")
@RequiredArgsConstructor
public class MessageTesterController {

    private final MessageTesterService messageTesterService;

    @PostMapping(
            path = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String addUser(@RequestBody UserAddRequest userAddRequest) {
        return messageTesterService.addUser(userAddRequest);
    }
}

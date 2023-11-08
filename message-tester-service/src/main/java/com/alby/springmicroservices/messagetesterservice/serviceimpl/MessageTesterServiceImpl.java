package com.alby.springmicroservices.messagetesterservice.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alby.springmicroservices.messagetesterservice.dto.request.AddUserRequest;
import com.alby.springmicroservices.messagetesterservice.service.MessageTesterService;

@Service
public class MessageTesterServiceImpl implements MessageTesterService {

    @Override
    public ResponseEntity<String> publishAdd(AddUserRequest addUserRequest) {
        
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}

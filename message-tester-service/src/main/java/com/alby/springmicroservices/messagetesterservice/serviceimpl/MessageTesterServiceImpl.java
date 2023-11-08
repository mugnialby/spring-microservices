package com.alby.springmicroservices.messagetesterservice.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alby.springmicroservices.messagetesterservice.dto.request.AddUserRequest;
import com.alby.springmicroservices.messagetesterservice.producer.MessageTesterProducer;
import com.alby.springmicroservices.messagetesterservice.service.MessageTesterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageTesterServiceImpl implements MessageTesterService {

    private MessageTesterProducer messageTesterProducer;

    @Override
    public ResponseEntity<String> publishAdd(AddUserRequest addUserRequest) {
        messageTesterProducer.sendMessage(addUserRequest);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}

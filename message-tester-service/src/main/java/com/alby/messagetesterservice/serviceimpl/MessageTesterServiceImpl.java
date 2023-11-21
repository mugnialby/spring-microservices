package com.alby.messagetesterservice.serviceimpl;

import com.alby.messagetesterservice.dto.users.request.UserAddRequest;
import com.alby.messagetesterservice.producer.UserProducer;
import com.alby.messagetesterservice.service.MessageTesterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageTesterServiceImpl implements MessageTesterService {

    private final UserProducer userProducer;

    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public String addUser(UserAddRequest userAddRequest) {
        try {
            userProducer.produce(objectMapper.writeValueAsString(userAddRequest), "UserAddRequest");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return "OK";
    }
}

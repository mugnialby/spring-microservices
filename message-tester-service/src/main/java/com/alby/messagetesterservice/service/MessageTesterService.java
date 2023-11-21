package com.alby.messagetesterservice.service;

import com.alby.messagetesterservice.dto.users.request.UserAddRequest;

public interface MessageTesterService {

    String addUser(UserAddRequest userAddRequest);
}

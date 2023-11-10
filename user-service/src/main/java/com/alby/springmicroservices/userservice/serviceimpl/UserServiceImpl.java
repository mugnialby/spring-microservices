package com.alby.springmicroservices.userservice.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.alby.springmicroservices.userservice.dto.request.AddUserRequest;
import com.alby.springmicroservices.userservice.dto.request.UpdateUserRequest;
import com.alby.springmicroservices.userservice.dto.response.UserResponse;
import com.alby.springmicroservices.userservice.dto.response.WebResponse;
import com.alby.springmicroservices.userservice.entity.User;
import com.alby.springmicroservices.userservice.repository.UserRepository;
import com.alby.springmicroservices.userservice.service.UserService;
import com.alby.springmicroservices.userservice.service.ValidationService;
import com.alby.springmicroservices.userservice.util.UsersUtil;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ValidationService validationService;

    @Override
    public WebResponse<List<UserResponse>> getAll(
        Optional<Integer> page, 
        Optional<Integer> size
    ) {
        Page<User> users = userRepository.findAll(
            PageRequest.of(
                page.orElse(0), 
                size.orElse(10), 
                Sort.by("firstName")
                    .ascending()
            ));

        List<UserResponse> userResponses = users
            .stream()
            .map(data -> new UsersUtil().mapUserToUserResponse(data))
            .collect(Collectors.toList());

        return WebResponse.<List<UserResponse>> builder()
            .message("OK")
            .data(userResponses)
            .build();
    }

    @Override
    public WebResponse<UserResponse> get(@Nonnull Long userId) {
        validationService.validate(userId);

        return WebResponse.<UserResponse> builder()
            .message("OK")
            .data(userRepository.findById(userId)   
                .map(data -> new UsersUtil().mapUserToUserResponse(data))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
            .build();
    }

    @Override
    @Transactional
    public void add(AddUserRequest request) {
        validationService.validate(request);

        if (userRepository.existsByUsername(request.getUsername()))
        throw new ResponseStatusException(HttpStatus.CONFLICT);

        userRepository.save(new UsersUtil().mapAddRequestToUsers(request));
    }

    @Override
    @Transactional
    public WebResponse<UserResponse> update(Long userId, UpdateUserRequest request) {
        validationService.validate(request);
        
        User userFromDb = userRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            
        if (Objects.nonNull(request.getUsername())) {
            if (!request.getUsername().equalsIgnoreCase(userFromDb.getUsername())
                && userRepository.existsByUsername(request.getUsername())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            userFromDb.setUsername(request.getUsername());
        }
    
        if (Objects.nonNull(request.getPassword())) {
            userFromDb.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }
    
        if (Objects.nonNull(request.getFirstName())) {
            userFromDb.setFirstName(request.getFirstName());
        }
    
        if (Objects.nonNull(request.getLastName())) {
            userFromDb.setLastName(request.getLastName());
        }
    
        if (Objects.nonNull(request.getStatus())) {
            userFromDb.setStatus(request.getStatus());
        }
    
        userRepository.save(userFromDb);

        return WebResponse.<UserResponse> builder()
            .message("OK")
            .data(new UsersUtil().mapUserToUserResponse(userFromDb))
            .build();
    }

    @Override
    @Transactional
    public void delete(@Nonnull Long userId) {
        validationService.validate(userId);

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        user.setStatus("N");
        userRepository.save(user);
    }
}

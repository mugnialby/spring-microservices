package com.alby.userservice.serviceimpl;

import com.alby.commonslib.proto.users.LoginRequest;
import com.alby.commonslib.proto.users.LoginResponse;
import com.alby.commonslib.proto.users.Users;
import com.alby.commonslib.proto.users.UsersServiceGrpc;
import com.alby.userservice.entity.UsersEntity;
import com.alby.userservice.repository.UserRepository;
import com.alby.userservice.service.ValidationService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@GrpcService
@RequiredArgsConstructor
public class UsersGrpcServiceImpl extends UsersServiceGrpc.UsersServiceImplBase {

    private final UserRepository userRepository;

    private final ValidationService validationService;

    @Override
    public void findByCredential(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        validationService.validate(request);

         UsersEntity usersFromDb = userRepository.findByUsername(request.getUsername())
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

         usersFromDb
    }
}

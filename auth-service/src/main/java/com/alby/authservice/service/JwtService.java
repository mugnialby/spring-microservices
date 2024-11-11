package com.alby.authservice.service;

import java.time.Instant;

public interface JwtService {

    String generateToken(String username);

    boolean validateToken(String token);
}

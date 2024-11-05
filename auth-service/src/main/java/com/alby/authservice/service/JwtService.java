package com.alby.authservice.service;

import java.time.Instant;

public interface JwtService {

    String generateToken(String username);

    String extractUsername(String token);

    Instant extractExpiration(String token);

    boolean validateToken(String token);
}

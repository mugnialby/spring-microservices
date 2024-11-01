package com.alby.authservice.serviceimpl;

import com.alby.authservice.configuration.jwt.JwtConfig;
import com.alby.authservice.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtConfig jwtConfig;

    @Override
    public String generateToken(String username) {
        Instant dateNow = Instant.now();
        Instant expiryDate = Instant.now().plus(jwtConfig.getExpiration(), ChronoUnit.HOURS);

        return Jwts.builder()
                .header()
                    .add("X-API-KEY", jwtConfig.getApiKey().getEncoded())
                    .and()
                .subject(username)
                .issuedAt(Date.from(dateNow))
                .expiration(Date.from(expiryDate))
                .signWith(jwtConfig.getSecretKey())
                .compact();
    }

    @Override
    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(jwtConfig.getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jws<Claims> jws = Jwts.parser().verifyWith(jwtConfig.getSecretKey()).build().parseSignedClaims(token);

            if (null == jws) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found");
            }

            if (!jwtConfig.getApiKey().getEncoded().equals(jws.getHeader().get("X-API-KEY"))) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token is invalid");
            }

            if (jws.getPayload().getExpiration().before(Date.from(Instant.now()))) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token is expired");
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

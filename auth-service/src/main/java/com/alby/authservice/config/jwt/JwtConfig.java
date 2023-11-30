package com.alby.authservice.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {

    @Value("${jwt.key.secret}")
    private String secretKey;

    @Value("${jwt.key.api}")
    private String apiKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Bean
    public SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Bean
    public SecretKey getApiKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.apiKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Bean
    public Long getExpiration() {
        return expiration;
    }
}

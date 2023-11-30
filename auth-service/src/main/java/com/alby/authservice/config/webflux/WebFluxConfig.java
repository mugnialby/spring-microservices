package com.alby.authservice.config.webflux;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebFluxConfig {

    @Value("${application.userservice.host}")
    private String userServiceHost;

    @Value("${application.userservice.port}")
    private String userServicePort;

    @Value("${application.userservice.version}")
    private String userServiceVersion;

    @Bean
    public String getUserServiceUrl() {
        return new StringBuilder()
                .append("http://")
                .append(userServiceHost)
                .append(":")
                .append(userServicePort)
                .append(new StringBuilder()
                        .append("/api/")
                        .append(userServiceVersion)
                        .append("/users/"))
                .toString();
    }

//    @Bean
//    public String getServiceUrl() {
//        return new StringBuilder()
//                .append("http://")
//                .append(userServiceHost)
//                .append(":")
//                .
//                .toString();
//    }
}

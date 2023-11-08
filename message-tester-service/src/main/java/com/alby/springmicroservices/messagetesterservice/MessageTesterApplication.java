package com.alby.springmicroservices.messagetesterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
	scanBasePackages = "com.alby.springmicroservices"
)
public class MessageTesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageTesterApplication.class, args);
	}

}

package com.alby.userservice.consumer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Service
//@RequiredArgsConstructor
public class UserConsumer {

//    private final UserService userService;
//
//    private final ObjectMapper objectMapper;
//
//    private final UserProducer userProducer;

//    @KafkaListener(
//            topics = "user.events"
//    )
    public void consume(
//            @Header(KafkaHeaders.CORRELATION_ID) String correlationId,
//            @Header(KafkaHeaders.RECEIVED_KEY) String receivedMessageKey,
//            String message
    ) {
//        if (receivedMessageKey.contains("loginRequest")) {
//            LoginRequest request;
//            try {
//                request = objectMapper.readValue(message, LoginRequest.class);
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//
//            try {
//                userProducer.produce(
//                        objectMapper.writeValueAsString(userService.getByUserNameAndPassword(request)),
//                        "loginRequest",
//                        correlationId
//                );
//            } catch (Exception e) {
//
//            }
//        }
    }
}

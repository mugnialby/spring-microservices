package com.alby.userservice.producer;


//@Service
//@Slf4j
//@RequiredArgsConstructor
public class UserProducer {

//    private final KafkaTemplate<String, String> kafkaTemplate;

//    public void produce(String jsonData, String messageKey, String correlationId) {
//        Message<String> message = MessageBuilder
//                .withPayload(jsonData)
//                .setHeader(KafkaHeaders.TOPIC,"user.events")
//                .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
//                .setHeader(KafkaHeaders.KEY, messageKey)
//                .build();
//
//        log.info(String.format("Message sent %s", message));
//        kafkaTemplate.send(message);
//    }
}

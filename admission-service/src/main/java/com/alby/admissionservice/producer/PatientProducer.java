package com.alby.admissionservice.producer;

import com.alby.admissionservice.util.AdmissionsUtil;
import com.alby.patientservice.dto.request.PatientAddRequest;
import com.alby.patientservice.entity.Patients;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatientProducer {

    private final KafkaTemplate<String, Patients> patientProducer;

    private final String patientEvents;

    public void sendPatientAddMessage(PatientAddRequest patientAddRequest) {
        Message<PatientAddRequest> message = MessageBuilder
                .withPayload(patientAddRequest)
                .setHeader(KafkaHeaders.CORRELATION_ID, AdmissionsUtil.getCorrelationId())
                .setHeader(KafkaHeaders.TOPIC, patientEvents)
                .build();

        log.info(String.format("Message sent %s", message));
        patientProducer.send(message);
    }
}

package com.alby.admissionservice.producer;

import com.alby.admissionservice.dto.request.patients.PatientAddRequest;
import com.alby.admissionservice.entity.Patients;
import com.alby.admissionservice.util.AdmissionsUtil;
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

    public void sendPatientAddMessage(PatientAddRequest patientAddRequest, String requestKey) {
        Message<PatientAddRequest> message = MessageBuilder
                .withPayload(patientAddRequest)
                .setHeader(KafkaHeaders.CORRELATION_ID, AdmissionsUtil.getCorrelationId())
                .setHeader(KafkaHeaders.TOPIC, patientEvents)
                .setHeader(KafkaHeaders.KEY, requestKey)
                .build();

        log.info(String.format("Message sent %s", message));
        patientProducer.send(message);
    }
}

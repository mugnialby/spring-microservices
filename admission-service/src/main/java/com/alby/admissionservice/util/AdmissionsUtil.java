package com.alby.admissionservice.util;

import com.alby.admissionservice.dto.request.admissions.AdmissionAddRequest;
import com.alby.admissionservice.dto.response.admissions.AdmissionResponse;
import com.alby.admissionservice.entity.Admissions;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;


@NoArgsConstructor
public class AdmissionsUtil {

    @Value("${spring.microservices.sender.id}")
    private static String senderId;

    public static String getCorrelationId() {
        return senderId +
                "." +
                UUID.randomUUID() +
                "." +
                System.currentTimeMillis();
    }
    
    public static AdmissionResponse mapAdmissionToAdmissionResponse(Admissions admission) {
        return AdmissionResponse.builder()
                .id(admission.getId())
                // .name(admission.getName())
                .status(admission.getStatus())
                .build();
    }

    public static Admissions mapAddRequestToAdmissions(AdmissionAddRequest request) {
        return Admissions.builder()
                .patients(request.getPatients())
                .createdBy(request.getCreatedBy())
                .build();
    }
}

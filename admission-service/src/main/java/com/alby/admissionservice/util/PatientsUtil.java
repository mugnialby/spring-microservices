package com.alby.admissionservice.util;

import com.alby.admissionservice.dto.request.patients.PatientAddRequest;
import com.alby.admissionservice.dto.response.patients.PatientResponse;
import com.alby.admissionservice.entity.Patients;

import lombok.Builder;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Builder
public class PatientsUtil {
    
    public static PatientResponse mapPatientToPatientResponse(Patients Patient) {
        return PatientResponse.builder()
                .id(Patient.getId())
                // .name(Patient.getName())
                .status(Patient.getStatus())
                .build();
    }

    public static Patients mapAddRequestToPatients(PatientAddRequest request) {
        return Patients.builder()
                // .name(request.getName())
                .createdBy(request.getCreatedBy())
                .build();
    }

    public static PatientAddRequest mapPatientsToPatientsAddRequest(Patients request) {
        return PatientAddRequest.builder()
                .name(request.getName())
                .createdBy(request.getCreatedBy())
                .build();
    }
}

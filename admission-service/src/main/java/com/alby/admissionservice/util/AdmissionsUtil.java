package com.alby.admissionservice.util;

import com.alby.admissionservice.dto.request.AdmissionAddRequest;
import com.alby.admissionservice.dto.response.AdmissionResponse;
import com.alby.admissionservice.entity.Admissions;

import lombok.Builder;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Builder
public class AdmissionsUtil {
    
    public static AdmissionResponse mapAdmissionToAdmissionResponse(Admissions admission) {
        return AdmissionResponse.builder()
                .id(admission.getId())
                // .name(admission.getName())
                .status(admission.getStatus())
                .build();
    }

    public static Admissions mapAddRequestToAdmissions(AdmissionAddRequest request) {
        return Admissions.builder()
                // .name(request.getName())
                .createdBy(request.getCreatedBy())
                .build();
    }
}

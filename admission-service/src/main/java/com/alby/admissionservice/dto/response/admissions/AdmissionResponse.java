package com.alby.admissionservice.dto.response.admissions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdmissionResponse {
    
    private Long id;

    private String name;

    private String status;
}

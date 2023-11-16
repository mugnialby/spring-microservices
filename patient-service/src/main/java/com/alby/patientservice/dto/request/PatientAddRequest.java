package com.alby.patientservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientAddRequest {
    
    // @NotBlank
    // @Length(max = 64)
    // private String name;

    @NotBlank
    @Length(max = 64)
    private String createdBy;
}

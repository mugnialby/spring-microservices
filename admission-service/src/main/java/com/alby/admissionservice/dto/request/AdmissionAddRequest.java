package com.alby.admissionservice.dto.request;

import com.alby.patientservice.entity.Patients;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdmissionAddRequest {

    @NotNull
    private Patients patients;

    @NotBlank
    @Length(max = 64)
    private String createdBy;
}

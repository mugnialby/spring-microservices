package com.alby.patientservice.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientUpdateRequest {

    @NotNull
    @Digits(integer = 12, fraction = 0)
    private Long id;
    
    @Length(max = 64)
    private String name;

    @Length(max = 1)
    private String status;

    @NotBlank
    @Length(max = 64)
    private String modifiedBy;
}

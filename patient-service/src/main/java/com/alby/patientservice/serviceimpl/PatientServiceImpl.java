package com.alby.patientservice.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.alby.patientservice.dto.request.*;
import com.alby.patientservice.dto.response.PatientResponse;
import com.alby.patientservice.dto.response.WebResponse;
import com.alby.patientservice.service.ValidationService;
import com.alby.patientservice.util.PatientsUtil;
import com.alby.patientservice.entity.Patients;
import com.alby.patientservice.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final com.alby.patientservice.repository.PatientRepository PatientRepository;

    private final ValidationService validationService;

    @Override
    public WebResponse<List<PatientResponse>> getAll(PatientPagingRequest request) {
        validationService.validate(request);

        Page<Patients> Patient = PatientRepository.findAll(
            PageRequest.of(
                request.getPage(),
                request.getPageSize(),
                Sort.by("id")
                    .ascending()
            ));

        List<PatientResponse> PatientResponses = Patient
            .stream()
            .map(PatientsUtil::mapPatientToPatientResponse)
            .collect(Collectors.toList());

        return WebResponse.<List<PatientResponse>> builder()
            .message("OK")
            .data(PatientResponses)
            .build();
    }

    @Override
    public WebResponse<PatientResponse> get(PatientGetRequest request) {
        validationService.validate(request);

        return WebResponse.<PatientResponse> builder()
            .message("OK")
            .data(PatientRepository.findById(request.getId())
                .map(PatientsUtil::mapPatientToPatientResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
            .build();
    }

    @Override
    @Transactional
    public WebResponse<PatientResponse> add(PatientAddRequest request) {
        validationService.validate(request);

        Patients Patient = PatientsUtil.mapAddRequestToPatients(request);
        PatientRepository.save(Patient);

        return WebResponse.<PatientResponse> builder()
                .message("OK")
                .data(PatientsUtil.mapPatientToPatientResponse(Patient))
                .build();
    }

    @Override
    @Transactional
    public WebResponse<PatientResponse> update(PatientUpdateRequest request) {
        validationService.validate(request);
        
        Patients PatientFromDb = PatientRepository.findById(request.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            
        // if (Objects.nonNull(request.getName())) {
            // if (!request.getName().equalsIgnoreCase(PatientFromDb.getName())
            //     && PatientRepository.existsByName(request.getName())) {
            //     throw new ResponseStatusException(HttpStatus.CONFLICT);
            // }

            // PatientFromDb.setName(request.getName());
        // }
    
        if (Objects.nonNull(request.getStatus())) {
            PatientFromDb.setStatus(request.getStatus());
        }

        PatientRepository.save(PatientFromDb);

        return WebResponse.<PatientResponse> builder()
            .message("OK")
            .data(PatientsUtil.mapPatientToPatientResponse(PatientFromDb))
            .build();
    }

    @Override
    @Transactional
    public WebResponse<String> delete(PatientDeleteRequest request) {
        validationService.validate(request);

        Patients Patient = PatientRepository.findById(request.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Patient.setStatus("N");
        Patient.setModifiedBy(request.getModifiedBy());
        PatientRepository.save(Patient);

        return WebResponse.<String> builder()
                .message("OK")
                .build();
    }
    
}

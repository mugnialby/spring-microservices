package com.alby.admissionservice.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.alby.admissionservice.dto.response.WebResponse;
import com.alby.admissionservice.dto.response.admissions.AdmissionResponse;
import com.alby.admissionservice.entity.Admissions;
import com.alby.admissionservice.producer.PatientProducer;
import com.alby.admissionservice.repository.AdmissionRepository;
import com.alby.admissionservice.service.AdmissionService;
import com.alby.admissionservice.service.ValidationService;
import com.alby.admissionservice.util.AdmissionsUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.alby.admissionservice.dto.request.admissions.AdmissionAddRequest;
import com.alby.admissionservice.dto.request.admissions.AdmissionDeleteRequest;
import com.alby.admissionservice.dto.request.admissions.AdmissionGetRequest;
import com.alby.admissionservice.dto.request.admissions.AdmissionPagingRequest;
import com.alby.admissionservice.dto.request.admissions.AdmissionUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdmissionServiceImpl implements AdmissionService {

    private final AdmissionRepository admissionRepository;

    private final ValidationService validationService;

    private final PatientProducer patientProducer;

//    private final

    @Override
    public WebResponse<List<AdmissionResponse>> getAll(AdmissionPagingRequest request) {
        validationService.validate(request);

        Page<Admissions> admission = admissionRepository.findAll(
            PageRequest.of(
                request.getPage(),
                request.getPageSize(),
                Sort.by("id")
                    .ascending()
            ));

        List<AdmissionResponse> admissionResponses = admission
            .stream()
            .map(AdmissionsUtil::mapAdmissionToAdmissionResponse)
            .collect(Collectors.toList());

        return WebResponse.<List<AdmissionResponse>> builder()
            .message("OK")
            .data(admissionResponses)
            .build();
    }

    @Override
    public WebResponse<AdmissionResponse> get(AdmissionGetRequest request) {
        validationService.validate(request);

        return WebResponse.<AdmissionResponse> builder()
            .message("OK")
            .data(admissionRepository.findById(request.getId())
                .map(AdmissionsUtil::mapAdmissionToAdmissionResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
            .build();
    }

    @Override
    @Transactional
    public WebResponse<AdmissionResponse> add(AdmissionAddRequest request) {
        validationService.validate(request);

        Admissions admission = AdmissionsUtil.mapAddRequestToAdmissions(request);
        admissionRepository.save(admission);

        return WebResponse.<AdmissionResponse> builder()
                .message("OK")
                .data(AdmissionsUtil.mapAdmissionToAdmissionResponse(admission))
                .build();
    }

    @Override
    @Transactional
    public WebResponse<AdmissionResponse> update(AdmissionUpdateRequest request) {
        validationService.validate(request);
        
        Admissions admissionFromDb = admissionRepository.findById(request.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            
        // if (Objects.nonNull(request.getName())) {
            // if (!request.getName().equalsIgnoreCase(admissionFromDb.getName())
            //     && admissionRepository.existsByName(request.getName())) {
            //     throw new ResponseStatusException(HttpStatus.CONFLICT);
            // }

            // admissionFromDb.setName(request.getName());
        // }
    
        if (Objects.nonNull(request.getStatus())) {
            admissionFromDb.setStatus(request.getStatus());
        }

        admissionRepository.save(admissionFromDb);

        return WebResponse.<AdmissionResponse> builder()
            .message("OK")
            .data(AdmissionsUtil.mapAdmissionToAdmissionResponse(admissionFromDb))
            .build();
    }

    @Override
    @Transactional
    public WebResponse<String> delete(AdmissionDeleteRequest request) {
        validationService.validate(request);

        Admissions admission = admissionRepository.findById(request.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        admission.setStatus("N");
        admission.setModifiedBy(request.getModifiedBy());
        admissionRepository.save(admission);

        return WebResponse.<String> builder()
                .message("OK")
                .build();
    }
    
}

package com.alby.patientservice.controller;

import java.util.List;

import com.alby.patientservice.dto.request.PatientAddRequest;
import com.alby.patientservice.dto.request.PatientDeleteRequest;
import com.alby.patientservice.dto.request.PatientPagingRequest;
import com.alby.patientservice.dto.response.PatientResponse;
import com.alby.patientservice.dto.response.WebResponse;
import com.alby.patientservice.service.PatientService;
import com.alby.patientservice.dto.request.PatientUpdateRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.alby.patientservice.dto.request.PatientGetRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {
    
    private final PatientService patientService;
    
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<PatientResponse>> getAll(@ModelAttribute PatientPagingRequest request) {
        return patientService.getAll(request);
    }
    
    @GetMapping(
        path = "/find",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PatientResponse> get(@ModelAttribute PatientGetRequest request) {
        return patientService.get(request);
    }
    
    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PatientResponse> add(@RequestBody PatientAddRequest request) {
        return patientService.add(request);
    }
    
    @PutMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PatientResponse> update(@RequestBody PatientUpdateRequest request) {
        return patientService.update(request);
    }
    
    @DeleteMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@RequestBody PatientDeleteRequest request) {
        return patientService.delete(request);
    }
}

package com.alby.admissionservice.controller;

import java.util.List;

import com.alby.admissionservice.dto.response.WebResponse;
import com.alby.admissionservice.dto.response.admissions.AdmissionResponse;
import com.alby.admissionservice.service.AdmissionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.alby.admissionservice.dto.request.admissions.AdmissionAddRequest;
import com.alby.admissionservice.dto.request.admissions.AdmissionDeleteRequest;
import com.alby.admissionservice.dto.request.admissions.AdmissionGetRequest;
import com.alby.admissionservice.dto.request.admissions.AdmissionPagingRequest;
import com.alby.admissionservice.dto.request.admissions.AdmissionUpdateRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admission")
@RequiredArgsConstructor
public class AdmissionController {
    
    private final AdmissionService admissionService;
    
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AdmissionResponse>> getAll(@ModelAttribute AdmissionPagingRequest request) {
        return admissionService.getAll(request);
    }
    
    @GetMapping(
        path = "/find",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AdmissionResponse> get(@ModelAttribute AdmissionGetRequest request) {
        return admissionService.get(request);
    }
    
    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AdmissionResponse> add(@RequestBody AdmissionAddRequest request) {
        return admissionService.add(request);
    }
    
    @PutMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AdmissionResponse> update(@RequestBody AdmissionUpdateRequest request) {
        return admissionService.update(request);
    }
    
    @DeleteMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@RequestBody AdmissionDeleteRequest request) {
        return admissionService.delete(request);
    }
}

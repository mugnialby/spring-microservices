package com.alby.admissionservice.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.alby.admissionservice.dto.request.AdmissionAddRequest;
import com.alby.admissionservice.dto.request.AdmissionDeleteRequest;
import com.alby.admissionservice.dto.request.AdmissionGetRequest;
import com.alby.admissionservice.dto.request.AdmissionPagingRequest;
import com.alby.admissionservice.dto.request.AdmissionUpdateRequest;
import com.alby.admissionservice.dto.response.AdmissionResponse;
import com.alby.springmicroservices.dto.response.WebResponse;
import com.alby.admissionservice.service.AdmissionService;

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

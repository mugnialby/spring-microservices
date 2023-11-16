package com.alby.admissionservice.service;

import java.util.List;

import com.alby.admissionservice.dto.request.AdmissionAddRequest;
import com.alby.admissionservice.dto.request.AdmissionDeleteRequest;
import com.alby.admissionservice.dto.request.AdmissionGetRequest;
import com.alby.admissionservice.dto.request.AdmissionPagingRequest;
import com.alby.admissionservice.dto.request.AdmissionUpdateRequest;
import com.alby.admissionservice.dto.response.AdmissionResponse;
import com.alby.springmicroservices.dto.response.WebResponse;

public interface AdmissionService {
    
    WebResponse<List<AdmissionResponse>> getAll(AdmissionPagingRequest request);

    WebResponse<AdmissionResponse> get(AdmissionGetRequest request);

    WebResponse<AdmissionResponse> add(AdmissionAddRequest request);

    WebResponse<AdmissionResponse> update(AdmissionUpdateRequest request);

    WebResponse<String> delete(AdmissionDeleteRequest request);
}

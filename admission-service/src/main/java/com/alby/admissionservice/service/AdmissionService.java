package com.alby.admissionservice.service;

import java.util.List;

import com.alby.admissionservice.dto.request.admissions.AdmissionAddRequest;
import com.alby.admissionservice.dto.request.admissions.AdmissionDeleteRequest;
import com.alby.admissionservice.dto.request.admissions.AdmissionUpdateRequest;
import com.alby.admissionservice.dto.request.admissions.AdmissionGetRequest;
import com.alby.admissionservice.dto.request.admissions.AdmissionPagingRequest;
import com.alby.admissionservice.dto.response.WebResponse;
import com.alby.admissionservice.dto.response.admissions.AdmissionResponse;

public interface AdmissionService {
    
    WebResponse<List<AdmissionResponse>> getAll(AdmissionPagingRequest request);

    WebResponse<AdmissionResponse> get(AdmissionGetRequest request);

    WebResponse<AdmissionResponse> add(AdmissionAddRequest request);

    WebResponse<AdmissionResponse> update(AdmissionUpdateRequest request);

    WebResponse<String> delete(AdmissionDeleteRequest request);
}

package com.alby.patientservice.service;

import com.alby.patientservice.dto.request.*;
import com.alby.patientservice.dto.response.PatientResponse;
import com.alby.patientservice.dto.response.WebResponse;

import java.util.List;

public interface PatientService {
    
    WebResponse<List<PatientResponse>> getAll(PatientPagingRequest request);

    WebResponse<PatientResponse> get(PatientGetRequest request);

    WebResponse<PatientResponse> add(PatientAddRequest request);

    WebResponse<PatientResponse> update(PatientUpdateRequest request);

    WebResponse<String> delete(PatientDeleteRequest request);
}

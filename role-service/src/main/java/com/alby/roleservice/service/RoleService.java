package com.alby.roleservice.service;

import java.util.List;

import com.alby.roleservice.dto.request.RoleAddRequest;
import com.alby.roleservice.dto.request.RoleDeleteRequest;
import com.alby.roleservice.dto.request.RoleGetRequest;
import com.alby.roleservice.dto.request.RolePagingRequest;
import com.alby.roleservice.dto.request.RoleUpdateRequest;
import com.alby.roleservice.dto.response.RoleResponse;
import com.alby.springmicroservices.dto.response.WebResponse;

public interface RoleService {
    
    WebResponse<List<RoleResponse>> getAll(RolePagingRequest request);

    WebResponse<RoleResponse> get(RoleGetRequest request);

    WebResponse<RoleResponse> add(RoleAddRequest request);

    WebResponse<RoleResponse> update(RoleUpdateRequest request);

    WebResponse<String> delete(RoleDeleteRequest request);
}

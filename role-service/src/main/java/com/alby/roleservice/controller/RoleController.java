package com.alby.roleservice.controller;

import java.util.List;

import com.alby.springmicroservices.dto.response.WebResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.alby.roleservice.dto.request.RoleAddRequest;
import com.alby.roleservice.dto.request.RoleDeleteRequest;
import com.alby.roleservice.dto.request.RoleGetRequest;
import com.alby.roleservice.dto.request.RolePagingRequest;
import com.alby.roleservice.dto.request.RoleUpdateRequest;
import com.alby.roleservice.dto.response.RoleResponse;
import com.alby.roleservice.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    
    private final RoleService roleService;
    
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<RoleResponse>> getAll(@ModelAttribute RolePagingRequest request) {
        return roleService.getAll(request);
    }
    
    @GetMapping(
        path = "/find",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<RoleResponse> get(@ModelAttribute RoleGetRequest request) {
        return roleService.get(request);
    }
    
    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<RoleResponse> add(@RequestBody RoleAddRequest request) {
        return roleService.add(request);
    }
    
    @PutMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<RoleResponse> update(@RequestBody RoleUpdateRequest request) {
        return roleService.update(request);
    }
    
    @DeleteMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@RequestBody RoleDeleteRequest request) {
        return roleService.delete(request);
    }
}

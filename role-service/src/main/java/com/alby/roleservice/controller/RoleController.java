package com.alby.roleservice.controller;

import java.util.List;

import com.alby.roleservice.dto.response.WebResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.alby.roleservice.dto.request.RoleSaveRequest;
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

    @CrossOrigin
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<RoleResponse>> findAll(RolePagingRequest request) {
        return roleService.getAll(request);
    }

    @CrossOrigin
    @GetMapping(
        path = "/find",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<RoleResponse> find(RoleGetRequest request) {
        return roleService.get(request);
    }

    @CrossOrigin
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> save(@RequestBody RoleSaveRequest request) {
        return roleService.save(request);
    }

    @CrossOrigin
    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<RoleResponse> update(@RequestBody RoleUpdateRequest request) {
        return roleService.update(request);
    }

    @CrossOrigin
    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable Long id) {
        return roleService.delete(id);
    }
}

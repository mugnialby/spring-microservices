package com.alby.roleservice.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.alby.roleservice.util.RolesUtil;
import com.alby.springmicroservices.dto.response.WebResponse;
import com.alby.springmicroservices.service.ValidationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.alby.roleservice.dto.request.RoleAddRequest;
import com.alby.roleservice.dto.request.RoleDeleteRequest;
import com.alby.roleservice.dto.request.RoleGetRequest;
import com.alby.roleservice.dto.request.RolePagingRequest;
import com.alby.roleservice.dto.request.RoleUpdateRequest;
import com.alby.roleservice.dto.response.RoleResponse;
import com.alby.roleservice.entity.Roles;
import com.alby.roleservice.repository.RoleRepository;
import com.alby.roleservice.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final ValidationService validationService;

    @Override
    public WebResponse<List<RoleResponse>> getAll(RolePagingRequest request) {
        validationService.validate(request);

        Page<Roles> roles = roleRepository.findAll(
            PageRequest.of(
                request.getPage(),
                request.getPageSize(),
                Sort.by("roleName")
                    .ascending()
            ));

        List<RoleResponse> roleResponses = roles
            .stream()
            .map(RolesUtil::mapRoleToRoleResponse)
            .collect(Collectors.toList());

        return WebResponse.<List<RoleResponse>> builder()
            .message("OK")
            .data(roleResponses)
            .build();
    }

    @Override
    public WebResponse<RoleResponse> get(RoleGetRequest request) {
        validationService.validate(request);

        return WebResponse.<RoleResponse> builder()
            .message("OK")
            .data(roleRepository.findById(request.getId())
                .map(RolesUtil::mapRoleToRoleResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
            .build();
    }

    @Override
    @Transactional
    public WebResponse<RoleResponse> add(RoleAddRequest request) {
        validationService.validate(request);

        if (roleRepository.existsByRoleName(request.getRoleName()))
            throw new ResponseStatusException(HttpStatus.CONFLICT);

        Roles role = RolesUtil.mapAddRequestToRoles(request);
        roleRepository.save(role);

        return WebResponse.<RoleResponse> builder()
                .message("OK")
                .data(RolesUtil.mapRoleToRoleResponse(role))
                .build();
    }

    @Override
    @Transactional
    public WebResponse<RoleResponse> update(RoleUpdateRequest request) {
        validationService.validate(request);
        
        Roles roleFromDb = roleRepository.findById(request.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            
        if (Objects.nonNull(request.getRoleName())) {
            if (!request.getRoleName().equalsIgnoreCase(roleFromDb.getRoleName())
                && roleRepository.existsByRoleName(request.getRoleName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            roleFromDb.setRoleName(request.getRoleName());
        }
    
        if (Objects.nonNull(request.getStatus())) {
            roleFromDb.setStatus(request.getStatus());
        }

        roleRepository.save(roleFromDb);

        return WebResponse.<RoleResponse> builder()
            .message("OK")
            .data(RolesUtil.mapRoleToRoleResponse(roleFromDb))
            .build();
    }

    @Override
    @Transactional
    public WebResponse<String> delete(RoleDeleteRequest request) {
        validationService.validate(request);

        Roles role = roleRepository.findById(request.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        role.setStatus("N");
        role.setModifiedBy(request.getModifiedBy());
        roleRepository.save(role);

        return WebResponse.<String> builder()
                .message("OK")
                .build();
    }
    
}

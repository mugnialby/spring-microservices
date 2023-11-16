package com.alby.roleservice.util;

import com.alby.roleservice.dto.request.RoleAddRequest;
import com.alby.roleservice.dto.response.RoleResponse;
import com.alby.roleservice.entity.Roles;

import lombok.Builder;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Builder
public class RolesUtil {
    
    public static RoleResponse mapRoleToRoleResponse(Roles role) {
        return RoleResponse.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .status(role.getStatus())
                .build();
    }

    public static Roles mapAddRequestToRoles(RoleAddRequest request) {
        return Roles.builder()
                .roleName(request.getRoleName())
                .createdBy(request.getCreatedBy())
                .build();
    }
}

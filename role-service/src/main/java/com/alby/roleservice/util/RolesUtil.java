package com.alby.roleservice.util;

import com.alby.roleservice.dto.request.RoleSaveRequest;
import com.alby.roleservice.entity.Roles;
import com.alby.roleservice.dto.response.RoleResponse;

import lombok.Builder;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Builder
public class RolesUtil {
    
    public static RoleResponse mapRoleToRoleResponse(Roles role) {
        return RoleResponse.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .status("Y".equals(role.getStatus()))
                .build();
    }

    public static Roles mapAddRequestToRoles(RoleSaveRequest request) {
        return Roles.builder()
                .roleName(request.getRoleName())
                .createdBy(request.getCreatedBy())
                .build();
    }
}

package com.alby.roleservice.spesification;

import com.alby.roleservice.entity.Roles;
import org.springframework.data.jpa.domain.Specification;

public class RoleSpecification {

    public static Specification<Roles> hasRoleName(String roleName) {
        return (root, query, builder) -> builder.equal(
                root.get("roleName"), roleName);
    }

    public static Specification<Roles> hasStatus(String status) {
        return (root, query, builder) -> builder.equal(
                root.get("status"), status);
    }
}

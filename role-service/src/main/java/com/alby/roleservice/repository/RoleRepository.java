package com.alby.roleservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.alby.roleservice.entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long>, JpaSpecificationExecutor<Roles> {

//    Page<Roles> findByRoleNameAndStatus(String roleName, String status);

    boolean existsByRoleName(String roleName);

    boolean existsByRoleNameAndIdNot(String roleName, Long id);
}

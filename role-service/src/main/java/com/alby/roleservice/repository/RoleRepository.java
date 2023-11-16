package com.alby.roleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alby.roleservice.entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long>{
    boolean existsByRoleName(String roleName);
}

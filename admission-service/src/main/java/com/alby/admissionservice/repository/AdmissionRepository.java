package com.alby.admissionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alby.admissionservice.entity.Admissions;

@Repository
public interface AdmissionRepository extends JpaRepository<Admissions, Long>{
    // boolean existsByName(String name);
}

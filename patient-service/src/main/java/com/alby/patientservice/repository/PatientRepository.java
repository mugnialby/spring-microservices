package com.alby.patientservice.repository;

import com.alby.patientservice.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patients, Long>{
    // boolean existsByName(String name);
}

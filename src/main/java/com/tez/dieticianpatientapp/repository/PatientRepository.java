package com.tez.dieticianpatientapp.repository;

import com.tez.dieticianpatientapp.entities.Dietician;
import com.tez.dieticianpatientapp.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Optional<Patient> findByUserTckn(String userTckn);
}

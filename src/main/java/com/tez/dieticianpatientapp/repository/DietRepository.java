package com.tez.dieticianpatientapp.repository;

import com.tez.dieticianpatientapp.entities.Diet;
import com.tez.dieticianpatientapp.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DietRepository extends JpaRepository<Diet,Long> {
    Optional<Diet> findByPatientId(Long patientId);
}

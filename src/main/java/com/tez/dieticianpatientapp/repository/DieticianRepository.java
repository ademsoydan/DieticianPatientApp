package com.tez.dieticianpatientapp.repository;

import com.tez.dieticianpatientapp.entities.Dietician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DieticianRepository extends JpaRepository<Dietician,Long> {
    Optional<Dietician> findByUserTckn(String userTckn);
}

package com.tez.dieticianpatientapp.repository;

import com.tez.dieticianpatientapp.entities.Dietician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DieticianRepository extends JpaRepository<Dietician,String> {
}

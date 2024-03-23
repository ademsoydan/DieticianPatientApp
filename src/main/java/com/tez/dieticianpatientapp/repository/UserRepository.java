package com.tez.dieticianpatientapp.repository;

import com.tez.dieticianpatientapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByTckn(String tckn);
}

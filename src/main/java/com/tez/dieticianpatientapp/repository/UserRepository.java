package com.tez.dieticianpatientapp.repository;

import com.tez.dieticianpatientapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByTckn(String tckn);
}

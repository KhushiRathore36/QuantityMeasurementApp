package com.quantitymeasurement.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.quantitymeasurement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

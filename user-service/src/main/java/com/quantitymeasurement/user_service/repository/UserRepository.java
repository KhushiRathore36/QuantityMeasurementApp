package com.quantitymeasurement.user_service.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.quantitymeasurement.user_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

	
}

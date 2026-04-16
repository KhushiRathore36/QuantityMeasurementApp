package com.quantitymeasurement.user_service.controller;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.quantitymeasurement.user_service.entity.User;
import com.quantitymeasurement.user_service.repository.UserRepository;
import com.quantitymeasurement.user_service.security.JwtUtil;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody User user) {

        // check if user already exists
        if (repo.findByUsername(user.getUsername()).isPresent()) {
           
            return Map.of("message", "User already exists");
        }

        // encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        repo.save(user);

        return Map.of("message", "User registered successfully");
    }

    
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ab token banao
        String token = jwtUtil.generateToken(user.getUsername(), user.getId());

        return Map.of("token", token);
    }
}

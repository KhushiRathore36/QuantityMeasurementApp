package com.quantitymeasurement.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.quantitymeasurement.entity.User;
import com.quantitymeasurement.repository.UserRepository;
import com.quantitymeasurement.security.JwtUtil;

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
    public String signup(@RequestBody User user) {

       
        if (repo.findByUsername(user.getUsername()).isPresent()) {
            return "User already exists";
        }

        
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        repo.save(user);

        return "User registered successfully";
    }

   
    @PostMapping("/login")
    public String login(@RequestBody User request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        return jwtUtil.generateToken(request.getUsername());
    }
}

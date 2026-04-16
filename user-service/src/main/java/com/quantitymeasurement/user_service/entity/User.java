package com.quantitymeasurement.user_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // getters setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    
    }
    public Long getId() {
    	return id;
    }
    // SETTERS
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

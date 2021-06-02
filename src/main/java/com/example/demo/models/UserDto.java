package com.example.demo.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private UserRole role;
    private LocalDateTime lastLogin;

    public enum UserRole {
        ROLE_ADMIN, ROLE_USER
    }
}

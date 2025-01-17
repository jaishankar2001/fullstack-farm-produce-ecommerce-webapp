package com.example.backend.dto.response;

import com.example.backend.entities.Role;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String refreshToken;
    private String firstname;

    private String lastname;
    private String email;
    private Role role;
    private double wallet_balance;
}

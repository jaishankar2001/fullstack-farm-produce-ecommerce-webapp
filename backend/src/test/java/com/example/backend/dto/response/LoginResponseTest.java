package com.example.backend.dto.response;

import org.junit.jupiter.api.Test;

import com.example.backend.entities.Role;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginResponseTest {
    @Test
    public void testGetterAndSetter() {
        LoginResponse response = new LoginResponse();

        String token = "testToken";
        String refreshToken = "testRefreshToken";
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        Role role = Role.CUSTOMER;
        double walletBalance = 100.0;

        response.setToken(token);
        response.setRefreshToken(refreshToken);
        response.setFirstname(firstName);
        response.setLastname(lastName);
        response.setEmail(email);
        response.setRole(role);
        response.setWallet_balance(walletBalance);

        assertEquals(token, response.getToken());
        assertEquals(refreshToken, response.getRefreshToken());
        assertEquals(firstName, response.getFirstname());
        assertEquals(lastName, response.getLastname());
        assertEquals(email, response.getEmail());
        assertEquals(role, response.getRole());
        assertEquals(walletBalance, response.getWallet_balance());
    }
}

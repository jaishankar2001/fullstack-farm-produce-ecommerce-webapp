package com.example.backend.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpRequestTest {
    @Test
    public void testGetterAndSetter() {
        // ARRANGE
        SignUpRequest request = new SignUpRequest();

        // Set values using setter methods
        String firstName = "John";
        String lastName = "Doe";
        String password = "password123";
        String email = "john.doe@example.com";
        com.example.backend.entities.Role role = com.example.backend.entities.Role.CUSTOMER;

        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setPassword(password);
        request.setEmail(email);
        request.setRole(role);

        // ACT
        // Verify that the getter methods return the correct values
        assertEquals(firstName, request.getFirstName());
        assertEquals(lastName, request.getLastName());
        assertEquals(password, request.getPassword());
        assertEquals(email, request.getEmail());
        assertEquals(role, request.getRole());
    }

    @Test
    public void testAllArgsConstructor() {
        // ARRANGE
        String firstName = "John";
        String lastName = "Doe";
        String password = "password123";
        String email = "john.doe@example.com";
        com.example.backend.entities.Role role = com.example.backend.entities.Role.CUSTOMER;

        // ACT
        // Create an instance using all args constructor
        SignUpRequest request = new SignUpRequest(firstName, lastName, password, email, role);

        // ASSERT
        // Verify that all fields are initialized correctly
        assertEquals(firstName, request.getFirstName());
        assertEquals(lastName, request.getLastName());
        assertEquals(password, request.getPassword());
        assertEquals(email, request.getEmail());
        assertEquals(role, request.getRole());
    }

    @Test
    public void testNoArgsConstructor() {
        // ARRANGE, ACT
        // Create an instance using no args constructor
        SignUpRequest request = new SignUpRequest();

        // ASSERT
        // Verify that all fields are initialized as null or default values
        assertEquals(null, request.getFirstName());
        assertEquals(null, request.getLastName());
        assertEquals(null, request.getPassword());
        assertEquals(null, request.getEmail());
        assertEquals(null, request.getRole());
    }
}

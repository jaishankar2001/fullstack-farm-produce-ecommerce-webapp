package com.example.backend.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignInRequestTest {

    @Test
    public void testGetterAndSetter() {
        // ARRANGE
        SignInRequest request = new SignInRequest();

        // Set email and password using setter methods
        String email = "test@example.com";
        String password = "password123";
        request.setEmail(email);
        request.setPassword(password);

        // ACT
        // Verify that the getter methods return the correct values
        assertEquals(email, request.getEmail());
        assertEquals(password, request.getPassword());
    }
}

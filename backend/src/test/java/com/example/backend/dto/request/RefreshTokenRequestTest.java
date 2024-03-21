package com.example.backend.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RefreshTokenRequestTest {
    @Test
    public void testGetterAndSetter() {
        // Create an instance of RefreshTokenRequest
        RefreshTokenRequest request = new RefreshTokenRequest();

        // Set a value for the token using the setter method
        String token = "testToken";
        request.setToken(token);

        // Verify that the getter method returns the correct value
        assertEquals(token, request.getToken());
    }
}

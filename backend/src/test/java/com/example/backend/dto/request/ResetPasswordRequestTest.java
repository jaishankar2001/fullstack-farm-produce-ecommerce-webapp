package com.example.backend.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResetPasswordRequestTest {
    @Test
    public void testGetterAndSetterAndConstructor() {
        String email = "test@example.com";
        ResetPasswordRequest request = new ResetPasswordRequest(email);

        assertEquals(email, request.getEmail());

        String newEmail = "newtest@example.com";
        request.setEmail(newEmail);

        assertEquals(newEmail, request.getEmail());
    }
}

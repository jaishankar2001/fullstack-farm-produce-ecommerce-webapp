package com.example.backend.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiResponseTest {
    @Test
    public void testGetterAndSetter() {
        ApiResponse response = new ApiResponse();

        String message = "Test message";
        response.setMessage(message);

        String actualMessage = response.getMessage();

        assertEquals(message, actualMessage);
    }
}

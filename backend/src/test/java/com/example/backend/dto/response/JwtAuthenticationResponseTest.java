package com.example.backend.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JwtAuthenticationResponseTest {
    @Test
    public void testGetterAndSetter() {
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();

        String token = "testToken";
        String refreshToken = "testRefreshToken";

        response.setToken(token);
        response.setRefreshToken(refreshToken);

        assertEquals(token, response.getToken());
        assertEquals(refreshToken, response.getRefreshToken());
    }
}

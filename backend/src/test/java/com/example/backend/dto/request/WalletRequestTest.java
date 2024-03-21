package com.example.backend.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WalletRequestTest {
    @Test
    public void testGetterAndSetter() {
        // ARRANGE
        WalletRequest request = new WalletRequest();

        String email = "test@example.com";
        Double amount = 100.0;

        request.setEmail(email);
        request.setAmount(amount);

        // ACT
        assertEquals(email, request.getEmail());
        assertEquals(amount, request.getAmount());
    }

}

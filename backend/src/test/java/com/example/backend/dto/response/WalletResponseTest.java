package com.example.backend.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WalletResponseTest {
    @Test
    public void testGetterAndSetter() {
        WalletResponse response = new WalletResponse();

        double balance = 100.0;

        response.setBalance(balance);

        assertEquals(balance, response.getBalance());
    }
}

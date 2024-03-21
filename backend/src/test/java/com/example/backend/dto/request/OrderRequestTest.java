package com.example.backend.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderRequestTest {
    @Test
    public void testGetterAndSetter() {
        // Create an instance of OrderRequest
        OrderRequest request = new OrderRequest();

        // Set values for the fields using the setter methods
        int customerId = 123;
        int farmId = 456;
        int productId = 789;
        int quantity = 10;

        request.setCustomer_id(customerId);
        request.setFarm_id(farmId);
        request.setProduct_id(productId);
        request.setQuantity(quantity);

        // Verify that the getter methods return the correct values
        assertEquals(customerId, request.getCustomer_id());
        assertEquals(farmId, request.getFarm_id());
        assertEquals(productId, request.getProduct_id());
        assertEquals(quantity, request.getQuantity());
    }
}

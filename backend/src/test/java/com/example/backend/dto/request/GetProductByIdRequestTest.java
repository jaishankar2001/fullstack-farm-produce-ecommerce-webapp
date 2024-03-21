package com.example.backend.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetProductByIdRequestTest {
    @Test
    public void testGetterAndSetter() {
        // Create an instance of GetProductByIdRequest
        GetProductByIdRequest request = new GetProductByIdRequest();

        // Set a value for the productId using the setter method
        int productId = 123;
        request.setProductId(productId);

        // Verify that the getter method returns the correct value
        assertEquals(productId, request.getProductId());
    }
}

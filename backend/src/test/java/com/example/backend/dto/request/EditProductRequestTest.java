package com.example.backend.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditProductRequestTest {
    @Test
    public void testGetterAndSetter() {
        // Create an instance of EditProductRequest
        EditProductRequest request = new EditProductRequest();

        // Set values using setter methods
        request.setId(1);
        request.setProductName("Product Name");
        request.setProductDescription("Product Description");
        request.setPrice(10.99);
        request.setStock(100);
        request.setUnit("kg");

        // Verify values using getter methods
        assertEquals(1, request.getId());
        assertEquals("Product Name", request.getProductName());
        assertEquals("Product Description", request.getProductDescription());
        assertEquals(10.99, request.getPrice());
        assertEquals(100, request.getStock());
        assertEquals("kg", request.getUnit());
    }
}

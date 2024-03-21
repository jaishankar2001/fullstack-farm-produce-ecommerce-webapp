package com.example.backend.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductSearchRequestTest {
    @Test
    public void testGetterAndSetter() {
        // ARRANGE
        ProductSearchRequest request = new ProductSearchRequest();

        // ACT
        String productName = "Test Product";
        request.setProductName(productName);

        // ASSERT
        assertEquals(productName, request.getProductName());
    }
}

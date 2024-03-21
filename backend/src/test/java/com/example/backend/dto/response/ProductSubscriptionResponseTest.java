package com.example.backend.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;
import java.util.ArrayList;

public class ProductSubscriptionResponseTest {
    @Test
    public void testGetterAndSetter() {
        ProductSubscriptionResponse response = new ProductSubscriptionResponse();

        int id = 1;
        String productName = "Test Product";
        String productDescription = "Test Description";
        double price = 10.0;
        int stock = 100;
        String unit = "kg";
        List<ImageDto> images = new ArrayList<>(); // Add image data if necessary

        response.setId(id);
        response.setProductName(productName);
        response.setProductDescription(productDescription);
        response.setPrice(price);
        response.setStock(stock);
        response.setUnit(unit);
        response.setImages(images);

        assertEquals(id, response.getId());
        assertEquals(productName, response.getProductName());
        assertEquals(productDescription, response.getProductDescription());
        assertEquals(price, response.getPrice());
        assertEquals(stock, response.getStock());
        assertEquals(unit, response.getUnit());
        assertEquals(images, response.getImages());
    }
}

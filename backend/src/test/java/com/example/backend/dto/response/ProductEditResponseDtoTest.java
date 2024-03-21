package com.example.backend.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class ProductEditResponseDtoTest {

    @Test
    public void testGetterAndSetter() {
        ProductEditResponseDto responseDto = new ProductEditResponseDto();

        String message = "Test message";
        List<ProductDto> products = new ArrayList<>(); // Add ProductDto data if necessary

        responseDto.setMessage(message);
        responseDto.setProduct(products);

        assertEquals(message, responseDto.getMessage());
        assertEquals(products, responseDto.getProduct());
    }
}

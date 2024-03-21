package com.example.backend.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class HomeResponseTest {
    @Test
    public void testGetterAndSetter() {
        HomeResponse response = new HomeResponse();

        List<FarmDto> farms = new ArrayList<>();
        List<ProductDto> products = new ArrayList<>();

        // Populate farms and products lists if necessary

        response.setFarms(farms);
        response.setProducts(products);

        assertEquals(farms, response.getFarms());
        assertEquals(products, response.getProducts());
    }
}

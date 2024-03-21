package com.example.backend.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddFarmRequestTest {
    @Test
    public void testGetterSetter() {
        AddFarmRequest request = new AddFarmRequest();

        // Set values using setter methods
        request.setName("Farm Name");
        request.setAddress("Farm Address");
        request.setDescription("Farm Description");
        request.setLat(123.456);
        request.setLng(456.789);

        // Verify values using getter methods
        assertEquals("Farm Name", request.getName());
        assertEquals("Farm Address", request.getAddress());
        assertEquals("Farm Description", request.getDescription());
        assertEquals(123.456, request.getLat());
        assertEquals(456.789, request.getLng());
    }
}

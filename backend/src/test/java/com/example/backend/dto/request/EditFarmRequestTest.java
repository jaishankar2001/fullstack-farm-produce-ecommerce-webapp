package com.example.backend.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditFarmRequestTest {
    @Test
    public void testGetterAndSetter() {
        EditFarmRequest request = new EditFarmRequest();

        // Set values using setter methods
        request.setId(1);
        request.setName("Farm Name");
        request.setAddress("Farm Address");
        request.setDescription("Farm Description");
        request.setLat(123.456);
        request.setLng(456.789);

        // Verify values using getter methods
        assertEquals(1, request.getId());
        assertEquals("Farm Name", request.getName());
        assertEquals("Farm Address", request.getAddress());
        assertEquals("Farm Description", request.getDescription());
        assertEquals(123.456, request.getLat());
        assertEquals(456.789, request.getLng());
    }

    @Test
    public void testNoArgsConstructor() {
        // Create an instance with no arguments
        EditFarmRequest request = new EditFarmRequest();

        // Verify that the instance is not null
        assertEquals(EditFarmRequest.class, request.getClass());
    }

    @Test
    public void testAllArgsConstructor() {
        // Create an instance with all arguments
        EditFarmRequest request = new EditFarmRequest(1, "Farm Name", "Farm Address", "Farm Description", 123.456,
                456.789);

        // Verify that the instance is not null and contains the correct values
        assertEquals(EditFarmRequest.class, request.getClass());
        assertEquals(1, request.getId());
        assertEquals("Farm Name", request.getName());
        assertEquals("Farm Address", request.getAddress());
        assertEquals("Farm Description", request.getDescription());
        assertEquals(123.456, request.getLat());
        assertEquals(456.789, request.getLng());
    }

}

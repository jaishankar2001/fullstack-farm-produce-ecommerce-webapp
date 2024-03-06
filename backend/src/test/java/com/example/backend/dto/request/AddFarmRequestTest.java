package com.example.backend.dto.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddFarmRequestTest {

    private AddFarmRequest addFarmRequest;

    @BeforeEach
    public void setUp() {
        addFarmRequest = new AddFarmRequest();
    }

    @Test
    public void testNameGetterAndSetter() {
        addFarmRequest.setName("Farm 1");
        assertEquals("Farm 1", addFarmRequest.getName());
    }

    @Test
    public void testAddressGetterAndSetter() {
        addFarmRequest.setAddress("123 Main St");
        assertEquals("123 Main St", addFarmRequest.getAddress());
    }

    @Test
    public void testDescriptionGetterAndSetter() {
        addFarmRequest.setDescription("A beautiful farm");
        assertEquals("A beautiful farm", addFarmRequest.getDescription());
    }

    @Test
    public void testLatGetterAndSetter() {
        addFarmRequest.setLat(40.7128);
        assertEquals(40.7128, addFarmRequest.getLat());
    }

    @Test
    public void testLngGetterAndSetter() {
        addFarmRequest.setLng(-74.0060);
        assertEquals(-74.0060, addFarmRequest.getLng());
    }
}

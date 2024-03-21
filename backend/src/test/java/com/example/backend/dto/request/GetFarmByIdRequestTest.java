package com.example.backend.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetFarmByIdRequestTest {

    @Test
    public void testGetterAndSetter() {
        // Create an instance of GetFarmByIdRequest
        GetFarmByIdRequest request = new GetFarmByIdRequest();

        // Set a value for the farmId using the setter method
        int farmId = 123;
        request.setFarmId(farmId);

        // Verify that the getter method returns the correct value
        assertEquals(farmId, request.getFarmId());
    }

}

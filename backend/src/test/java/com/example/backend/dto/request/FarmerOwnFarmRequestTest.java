package com.example.backend.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FarmerOwnFarmRequestTest {
    @Test
    public void testGetterAndSetter() {
        FarmerOwnFarmRequest request = new FarmerOwnFarmRequest();

        String searchTerm = "test";
        request.setSearchTerm(searchTerm);

        assertEquals(searchTerm, request.getSearchTerm());

        request.setSearchTerm(null);

        assertEquals(null, request.getSearchTerm());
    }
}

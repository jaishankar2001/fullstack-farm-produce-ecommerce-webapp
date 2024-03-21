package com.example.backend.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

import java.util.ArrayList;

public class GetFarmByIdResponseTest {

    @Test
    public void testGetterAndSetter() {
        GetFarmByIdResponse response = new GetFarmByIdResponse();

        String name = "Test Farm";
        String address = "123 Test Street";
        double lat = 40.7128;
        double lng = -74.0060;
        String description = "Test Description";
        List<ImageDto> images = new ArrayList<>(); // Add image data if necessary

        response.setName(name);
        response.setAddress(address);
        response.setLat(lat);
        response.setLng(lng);
        response.setDescription(description);
        response.setImages(images);

        assertEquals(name, response.getName());
        assertEquals(address, response.getAddress());
        assertEquals(lat, response.getLat());
        assertEquals(lng, response.getLng());
        assertEquals(description, response.getDescription());
        assertEquals(images, response.getImages());
    }
}

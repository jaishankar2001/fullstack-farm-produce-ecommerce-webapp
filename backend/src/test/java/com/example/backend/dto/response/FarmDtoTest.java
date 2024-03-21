package com.example.backend.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

import java.util.ArrayList;

public class FarmDtoTest {
    @Test
    public void testGetterAndSetter() {
        FarmDto farmDto = new FarmDto();

        int id = 1;
        String name = "Test Farm";
        String address = "123 Test Street";
        String description = "Test Description";
        double lat = 40.7128;
        double lng = -74.0060;
        List<ImageDto> images = new ArrayList<>(); // Add image data if necessary

        farmDto.setId(id);
        farmDto.setName(name);
        farmDto.setAddress(address);
        farmDto.setDescription(description);
        farmDto.setLat(lat);
        farmDto.setLng(lng);
        farmDto.setImages(images);

        assertEquals(id, farmDto.getId());
        assertEquals(name, farmDto.getName());
        assertEquals(address, farmDto.getAddress());
        assertEquals(description, farmDto.getDescription());
        assertEquals(lat, farmDto.getLat());
        assertEquals(lng, farmDto.getLng());
        assertEquals(images, farmDto.getImages());
    }
}

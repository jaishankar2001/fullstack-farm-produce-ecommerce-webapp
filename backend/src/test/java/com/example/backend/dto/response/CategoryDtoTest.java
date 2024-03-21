package com.example.backend.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryDtoTest {
    @Test
    public void testGetterAndSetter() {
        CategoryDto categoryDto = new CategoryDto();

        int id = 1;
        String name = "Test Category";

        categoryDto.setId(id);
        categoryDto.setName(name);

        assertEquals(id, categoryDto.getId());
        assertEquals(name, categoryDto.getName());
    }
}

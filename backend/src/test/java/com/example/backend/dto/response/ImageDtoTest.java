package com.example.backend.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageDtoTest {
    @Test
    public void testGetterAndSetter() {
        ImageDto imageDto = new ImageDto();

        int id = 1;
        String imgUrl = "https://example.com/image.jpg";

        imageDto.setId(id);
        imageDto.setImg_url(imgUrl);

        assertEquals(id, imageDto.getId());
        assertEquals(imgUrl, imageDto.getImg_url());
    }
}

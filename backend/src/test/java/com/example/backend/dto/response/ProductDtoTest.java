package com.example.backend.dto.response;

import org.junit.jupiter.api.Test;

import com.example.backend.entities.Category;
import com.example.backend.entities.Farms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class ProductDtoTest {
    @Test
    public void testGetterAndSetter() {
        ProductDto productDto = new ProductDto();

        int id = 1;
        String productName = "Test Product";
        String productDescription = "Test Description";
        double price = 10.0;
        int stock = 100;
        String unit = "kg";
        CategoryDto category = new CategoryDto();
        Category productCategory = new Category();

        Farms farm = new Farms(); // Assuming Farms class exists
        boolean prebook = true;
        List<ImageDto> images = new ArrayList<>(); // Add image data if necessary

        productDto.setId(id);
        productDto.setProductName(productName);
        productDto.setProductDescription(productDescription);
        productDto.setPrice(price);
        productDto.setStock(stock);
        productDto.setUnit(unit);
        productDto.setCategory(category);
        productDto.setProductCategory(productCategory);
        productDto.setFarm(farm);
        productDto.setPrebook(prebook);
        productDto.setImages(images);

        assertEquals(id, productDto.getId());
        assertEquals(productName, productDto.getProductName());
        assertEquals(productDescription, productDto.getProductDescription());
        assertEquals(price, productDto.getPrice());
        assertEquals(stock, productDto.getStock());
        assertEquals(unit, productDto.getUnit());
        assertEquals(productCategory, productDto.getProductCategory());
        assertEquals(farm, productDto.getFarm());
        assertEquals(prebook, productDto.isPrebook());
        assertEquals(images, productDto.getImages());
    }
}

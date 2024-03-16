package com.example.backend.utils;

import org.springframework.stereotype.Service;

import com.example.backend.dto.response.CategoryDto;
import com.example.backend.dto.response.FarmDto;
import com.example.backend.dto.response.ProductDto;
import com.example.backend.entities.Category;
import com.example.backend.entities.Farms;
import com.example.backend.entities.Images;
import com.example.backend.entities.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResponseUtils {

    public static FarmDto convertFarmResponse(Farms current_farm) {
        FarmDto farmDTO = new FarmDto();
        farmDTO.setId(current_farm.getId());
        farmDTO.setName(current_farm.getName());
        farmDTO.setAddress(current_farm.getAddress());
        farmDTO.setDescription(current_farm.getDescription());
        farmDTO.setLat(current_farm.getLat());
        farmDTO.setLng(current_farm.getLng());

        for (Images images : current_farm.getImages()) {
            farmDTO.addImage(images);
        }

        return farmDTO;
    }

    public static ProductDto convertProductResponse(Product product) {
        long startTime = System.nanoTime();
        ProductDto dto = new ProductDto();
        dto.setProductName(product.getProductName());
        dto.setId(product.getId());
        dto.setStock(product.getStock());
        dto.setPrice(product.getPrice());
        dto.setUnit(product.getUnit());
        dto.setProductDescription(product.getProductDescription());

        Category category = product.getCategory();

        for (Images images : product.getImages()) {
            dto.addImage(images);
        }

        long afterS3 = System.nanoTime();
        if (category != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            dto.setCategory(categoryDto);
        }
        return dto;
    }

}

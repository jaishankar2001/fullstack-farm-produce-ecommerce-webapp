package com.example.backend.dto.response;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProductEditResponseDto {
    private String message;
    private List<ProductDto>product;
}

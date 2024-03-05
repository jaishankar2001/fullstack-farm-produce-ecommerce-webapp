package com.example.backend.dto.request;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
/**
 * EditProductRequest
 */
public class EditProductRequest {
    @NotNull(message = "Product ID is required")
    private int id;
    private String productName;
    private String productDescription;
    private BigDecimal price;
    private int stock;
    private String unit;
}
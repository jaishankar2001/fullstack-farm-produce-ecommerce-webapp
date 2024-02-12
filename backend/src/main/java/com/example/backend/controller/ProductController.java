package com.example.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.request.AddProductRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.ProductDto;
import com.example.backend.services.ProductService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.security.Principal;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/addproduct")
    public ResponseEntity<Map> addProduct(@ModelAttribute @Valid AddProductRequest product,
            @RequestPart(value = "files") MultipartFile[] files, Principal principal) {
        List<ProductDto> AllFarmProducts = productService.addProduct(product, files,
                principal);
        Map<String, Object> response = new HashMap<>();

        response.put("message", principal.getName());
        response.put("Products", AllFarmProducts);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        ApiResponse response = new ApiResponse();
        response.setMessage("product deleted successfully");
        return ResponseEntity.ok(response);
    }
}
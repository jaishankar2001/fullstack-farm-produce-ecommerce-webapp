package com.example.backend.services;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.security.Principal;
import com.example.backend.dto.request.AddProductRequest;
import com.example.backend.dto.request.ProductSearchRequest;
import com.example.backend.dto.response.ProductDto;

public interface ProductService {
    List<ProductDto> addProduct(AddProductRequest product, MultipartFile[] files, Principal principal);

    void deleteProduct(int id);

    List<ProductDto> getFarmerProducts(Principal principal);

    List<ProductDto> getAllProducts(ProductSearchRequest productSearchRequest);
}

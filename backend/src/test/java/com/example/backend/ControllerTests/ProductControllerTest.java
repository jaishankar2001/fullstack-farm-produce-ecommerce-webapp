package com.example.backend.ControllerTests;

import com.example.backend.controller.ProductController;
import com.example.backend.dto.request.AddProductRequest;
import com.example.backend.dto.request.EditProductRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.ProductDto;
import com.example.backend.entities.Product;
import com.example.backend.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProduct() {
        AddProductRequest productRequest = new AddProductRequest();
        MultipartFile[] files = new MultipartFile[1]; // mock files if necessary
        Principal principal = mock(Principal.class);
        List<ProductDto> allFarmProducts = new ArrayList<>(); // create sample products

        // Assuming productService returns products without any issues
        when(productService.addProduct(productRequest, files, principal)).thenReturn(allFarmProducts);

        // Act
        ResponseEntity<Map> responseEntity = productController.addProduct(productRequest, files, principal);

        // Assert
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void testEditProduct() {
        EditProductRequest productRequest = new EditProductRequest();
        MultipartFile[] files = new MultipartFile[1];
        Principal principal = mock(Principal.class);
        Product product = new Product();

        when(productService.editProduct(productRequest, files, principal)).thenReturn(product);

        // Act
        ResponseEntity<Product> responseEntity = productController.editProduct(productRequest, files, principal);

        // Assert
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void testDeleteProduct() {
        // Assuming productService deletes the product without any issues
        doNothing().when(productService).deleteProduct(1);

        // Act
        ResponseEntity<ApiResponse> responseEntity = productController.deleteProduct(1);

        // Assert
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }
}

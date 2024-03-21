package com.example.backend.Controller;

import com.example.backend.controller.ProductController;
import com.example.backend.dto.request.AddProductRequest;
import com.example.backend.dto.request.EditProductRequest;
import com.example.backend.dto.request.ProductSearchRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.ProductDto;
import com.example.backend.entities.Product;
import com.example.backend.services.ProductService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductControllerTest {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMultipartFile[] mockFiles;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        byte[] content = new byte[1];
        MockMultipartFile mockMultipartFile = new MockMultipartFile("Dummy-file", content);
        mockFiles = new MockMultipartFile[] { mockMultipartFile };
    }

    @Test
    public void testGetFarmerProducts() {
        Principal principal = mock(Principal.class);
        List<ProductDto> userProducts = Arrays.asList(new ProductDto());
        when(productService.getFarmerProducts(principal)).thenReturn(userProducts);

        ResponseEntity<List<ProductDto>> responseEntity = productController.getFarmerProducts(principal);

        // ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userProducts, responseEntity.getBody());
    }

    @Test
    public void testGetAllProducts() {
        ProductSearchRequest productSearchRequest = new ProductSearchRequest();
        List<ProductDto> allProducts = Arrays.asList(new ProductDto());
        when(productService.getAllProducts(productSearchRequest)).thenReturn(allProducts);

        ResponseEntity<List<ProductDto>> responseEntity = productController.getAllProducts(productSearchRequest);

        // ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(allProducts, responseEntity.getBody());
    }

    @Test
    public void testAddProduct() {
        Principal principal = mock(Principal.class);
        AddProductRequest productRequest = new AddProductRequest();
        List<ProductDto> allFarmProducts = Arrays.asList(new ProductDto());

        when(productService.addProduct(productRequest, mockFiles, principal)).thenReturn(allFarmProducts);

        ResponseEntity<Map> responseEntity = productController.addProduct(productRequest, mockFiles, principal);

        // Verify response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(principal.getName(), responseEntity.getBody().get("message"));
        assertEquals(allFarmProducts, responseEntity.getBody().get("Products"));

    }

    @Test
    public void testEditProduct() {
        Principal principal = mock(Principal.class);
        EditProductRequest editProductRequest = new EditProductRequest();
        Product editedProduct = new Product();

        // ARRANGE
        when(productService.editProduct(editProductRequest, mockFiles, principal)).thenReturn(editedProduct);

        ResponseEntity<Product> responseEntity = productController.editProduct(editProductRequest, mockFiles,
                principal);

        // Verify response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(editedProduct, responseEntity.getBody());
    }

    @Test
    public void testDeleteProduct() {
        int productId = 1;

        doNothing().when(productService).deleteProduct(productId);
        ResponseEntity<ApiResponse> responseEntity = productController.deleteProduct(productId);

        verify(productService).deleteProduct(productId);

        // ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("product deleted successfully", responseEntity.getBody().getMessage());
    }

    @Test
    public void testGetProductById() {
        int productId = 1;
        ProductDto productDto = new ProductDto();

        // ARRANGE
        when(productService.getProductById(productId)).thenReturn(productDto);

        ResponseEntity<ProductDto> responseEntity = productController.getProductById(productId);

        // Verify response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productDto, responseEntity.getBody());

    }
}

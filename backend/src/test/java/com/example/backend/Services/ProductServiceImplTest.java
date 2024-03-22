package com.example.backend.Services;

import com.example.backend.dto.request.AddProductRequest;
import com.example.backend.dto.request.EditProductRequest;
import com.example.backend.dto.response.ProductDto;
import com.example.backend.entities.Category;
import com.example.backend.entities.Farms;
import com.example.backend.entities.Images;
import com.example.backend.entities.Product;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.*;
import com.example.backend.services.ProductService;
import com.example.backend.services.impl.ProductServiceImpl;
import com.example.backend.utils.Awsutils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private FarmRepository farmRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ImagesRepository imagesRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private Awsutils awsutils;

    @InjectMocks
    private AddProductRequest addProductRequest;
    @InjectMocks
    ProductServiceImpl productService;
    @InjectMocks
    Farms farm;
    @InjectMocks
    Category category;
    @InjectMocks
    Images images;

    @Mock
    Product product;

    @InjectMocks
    ProductDto productDto;

    static final int id = 1;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);
        addProductRequest.setFarm_id(id);
        farm.setId(addProductRequest.getFarm_id());
        addProductRequest.setCategory_id(id);
        category.setId(addProductRequest.getCategory_id());
        product = new Product();
        when(modelMapper.map(addProductRequest, Product.class)).thenReturn(product);
        Product savedProduct = new Product();
        savedProduct.setId(1); // Set an ID for the saved product
        when(productRepository.save(any())).thenReturn(savedProduct);
        when(farmRepository.findById(anyInt())).thenReturn(farm);
        when(categoryRepository.findById(anyInt())).thenReturn(category);
        when(awsutils.uploadFileToS3(any(MultipartFile.class), anyString(), anyInt())).thenReturn("test-url");
        product.setId(id);
        ArrayList<Images> image = new ArrayList<>();
        image.add(images);
        imagesRepository.save(images);
        product.setImages(image);
    }

    @Test
    void testAddProduct() {
        Principal principal = () -> "test@example.com";
        MockMultipartFile[] mockFiles = {new MockMultipartFile("file1", new byte[0]), new MockMultipartFile("file2", new byte[0])};
        List<ProductDto> productAdded = productService.addProduct(addProductRequest, mockFiles, principal);
        assertTrue(productAdded instanceof List<ProductDto>);
    }

    @Test
    void testDeleteProduct(){

        when(productRepository.findById(id)).thenReturn(product);
        productService.deleteProduct(id);
        verify(productRepository, times(id)).deleteById(id);

    }

    @Test
    void testDeleteProductWhenNullTest(){
        when(productRepository.findById(anyInt())).thenReturn(null);
        assertThrows(ApiRequestException.class,()-> productService.deleteProduct(anyInt()));
    }

    @Test
    void testEditProduct(){

        EditProductRequest mockEditProductRequest = new EditProductRequest();
        mockEditProductRequest.setId(2); // Set valid ID for testing
        mockEditProductRequest.setProductName("Updated Product Name");
        mockEditProductRequest.setProductDescription("Updated Product Description");
        mockEditProductRequest.setPrice(99.99);
        mockEditProductRequest.setStock(100);
        mockEditProductRequest.setUnit("kg");

        MultipartFile[] mockFiles = { new MockMultipartFile("file1", "test.txt", "text/plain", "test file".getBytes()) };
        Principal mockPrincipal = () -> "user1";

        when(productRepository.findById(anyInt())).thenReturn(product);
        when(awsutils.uploadFileToS3(any(MultipartFile.class), anyString(), anyInt())).thenReturn("https://test-URL/test/file1");


        Product updatedProduct = productService.editProduct(mockEditProductRequest, mockFiles, mockPrincipal);

        assertNotNull(updatedProduct);
        assertEquals(mockEditProductRequest.getProductName(), updatedProduct.getProductName());
        assertEquals(mockEditProductRequest.getProductDescription(), updatedProduct.getProductDescription());
        assertEquals(mockEditProductRequest.getPrice(), updatedProduct.getPrice(), 0.0);
        assertEquals(mockEditProductRequest.getStock(), updatedProduct.getStock());
        assertEquals(mockEditProductRequest.getUnit(), updatedProduct.getUnit());
    }

    @Test
    void testEditProductWhenNull(){
        MultipartFile[] mockFiles = { new MockMultipartFile("file1", "test.txt", "text/plain", "test file".getBytes()) };
        Principal mockPrincipal = () -> "user1";
        EditProductRequest mockEditProductRequest = new EditProductRequest();
        when(productRepository.findById(anyInt())).thenReturn(null);
        assertThrows(ApiRequestException.class, () -> productService.editProduct(mockEditProductRequest, mockFiles, mockPrincipal));
    }

    @Test
    void testGetProductById(){
        when(productRepository.findById(id)).thenReturn(product);
        Object result = productService.getProductById(id);
        assertEquals(productService.getProductById(id).getId(), productDto.getId());
    }

    @Test
    void testProductByIdWhenNull(){
        when(productRepository.findById(anyInt())).thenReturn(null);
        assertThrows(ApiRequestException.class, ()-> productService.getProductById(id));
    }


}

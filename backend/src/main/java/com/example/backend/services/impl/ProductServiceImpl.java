package com.example.backend.services.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import com.example.backend.dto.request.AddProductRequest;
import com.example.backend.dto.request.ProductSearchRequest;
import com.example.backend.dto.response.CategoryDto;
import com.example.backend.dto.response.ProductDto;
import com.example.backend.entities.Category;
import com.example.backend.entities.Farms;
import com.example.backend.entities.Images;
import com.example.backend.entities.Product;
import com.example.backend.entities.User;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.FarmRepository;
import com.example.backend.repository.ImagesRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.services.ProductService;
import com.example.backend.repository.UserRepository;
import com.example.backend.utils.Awsutils;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final FarmRepository farmRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Awsutils awsutils;
    private final ImagesRepository imagesRepository;

    @Override
    public List<ProductDto> addProduct(AddProductRequest AddProductRequest, final MultipartFile[] multipartFiles,
            Principal principal) {
        try {

            Farms farm = farmRepository.findById(AddProductRequest.getFarm_id());

            Category category = categoryRepository.findById(AddProductRequest.getCategory_id());

            Product product = modelMapper.map(AddProductRequest, Product.class);
            product.setFarm(farm);
            product.setCategory(category);
            Product savedProduct = productRepository.save(product);

            List<Images> productImages = new ArrayList<>();
            for (MultipartFile file : multipartFiles) {
                String url = awsutils.uploadFileToS3(file, "PRODUCT", savedProduct.getId());
                Images image = new Images();
                image.setProduct(savedProduct);
                image.setImg_url(url);
                imagesRepository.save(image);
                System.out.println("-=-=-=-=- " + url);
                productImages.add(image);
            }
            product.setImages(productImages);

            List<Product> userProducts = productRepository.findByFarm(farm);
            return userProducts.stream().map(this::convertProductResponse).collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            List<ProductDto> products = new ArrayList<>();
            return products;
        }
    }

    private ProductDto convertProductResponse(Product product) {
        long startTime = System.nanoTime();
        ProductDto dto = new ProductDto();
        dto.setProductName(product.getProductName());
        dto.setId(product.getId());
        dto.setStock(product.getStock());
        dto.setPrice(product.getPrice());
        dto.setUnit(product.getUnit());

        Category category = product.getCategory();

        for (Images images : product.getImages()) {
            dto.addImage(images);
        }

        long afterS3 = System.nanoTime();
        System.out.println("Time AFTERR IMAGES DTOOOOO: " + (afterS3 - startTime) / 1e6 + " milliseconds");
        if (category != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            dto.setCategory(categoryDto);
        }
        return dto;
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ApiRequestException("Product not found");
        }
        List<Images> productImages = product.getImages();
        for (Images image : productImages) {
            imagesRepository.deleteById(image.getId());
        }
        // deleting product
        productRepository.deleteById(product.getId());

    }

      @Override
    public List<ProductDto> getFarmerProducts(Principal principal) {
          User user = userRepository.findByEmail(principal.getName());
        if (user == null) {
            throw new ApiRequestException("User not found");
        }
        List<Farms> userFarms = farmRepository.findByUser(user);
        List<Product> allProducts = new ArrayList<>();

    for (Farms farm : userFarms) {
        List<Product> products = productRepository.findByFarm(farm);
        allProducts.addAll(products);
    }
    return allProducts.stream().map(this::convertProductResponse).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProducts(ProductSearchRequest productSearchRequest) {
        List<Product> allProducts = new ArrayList<>();;
        String productName = productSearchRequest.getProductName();
        if(!Objects.equals(productName, "")){
            allProducts = productRepository.findByProductNameContaining(productName);
            if(allProducts.isEmpty()){
                allProducts = productRepository.findAll();
            }
        }else {
            allProducts = productRepository.findAll();
        }
        return allProducts.stream().map(this::convertProductResponse).collect(Collectors.toList());
    }

}

package com.example.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.backend.dto.response.FarmDto;
import com.example.backend.dto.response.HomeResponse;
import com.example.backend.dto.response.ProductDto;
import com.example.backend.entities.Farms;
import com.example.backend.entities.Product;
import com.example.backend.repository.FarmRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.services.HomeService;
import com.example.backend.utils.ResponseUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final FarmRepository farmRepository;
    private final ProductRepository productRepository;

    @Override
    public HomeResponse getHomeMeta() {
        List<Farms> topFarms = farmRepository.findTop8ByOrderByIdDesc();
        List<FarmDto> farms = topFarms.stream().map(ResponseUtils::convertFarmResponse).collect(Collectors.toList());

        List<Product> latestProducts = productRepository.findTop8ByOrderByIdDesc();
        List<ProductDto> products = latestProducts.stream().map(ResponseUtils::convertProductResponse)
                .collect(Collectors.toList());

        HomeResponse homeResponse = new HomeResponse();
        homeResponse.setFarms(farms);
        homeResponse.setProducts(products);
        return homeResponse;
    }

}

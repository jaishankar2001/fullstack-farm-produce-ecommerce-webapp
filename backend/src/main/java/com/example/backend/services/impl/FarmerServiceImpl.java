package com.example.backend.services.impl;

import com.example.backend.dto.request.EditFarmRequest;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import com.example.backend.dto.request.AddFarmRequest;
import com.example.backend.dto.response.FarmDto;
import com.example.backend.entities.Farms;
import com.example.backend.entities.Images;
import com.example.backend.entities.Product;
import com.example.backend.entities.User;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.FarmRepository;
import com.example.backend.repository.ImagesRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.FarmerService;
import com.example.backend.utils.Awsutils;

@Service
@RequiredArgsConstructor
public class FarmerServiceImpl implements FarmerService {
    private final UserRepository userRepository;
    private final FarmRepository farmRepository;
    private final ModelMapper modelMapper;
    private final Awsutils awsutils;
    private final ImagesRepository imagesRepository;
    private final ProductRepository productRepository;

    @Override
    public List<FarmDto> addFarm(AddFarmRequest farmRequest, final MultipartFile[] multipartFiles,
            Principal principal) {
        User user = userRepository.findByEmail(principal.getName());

        Farms farm = modelMapper.map(farmRequest, Farms.class);
        System.out.println(farm);
        farm.setUser(user);
        Farms savedFarm = farmRepository.save(farm);
        System.out.println(savedFarm.getId());

        List<Images> farmImages = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            String url = awsutils.uploadFileToS3(file, "FARM", savedFarm.getId());
            Images image = new Images();
            image.setFarm(savedFarm);
            image.setImg_url(url);
            imagesRepository.save(image);
            System.out.println("-=-=-=-=-  " + url);
            farmImages.add(image);
        }
        farm.setImages(farmImages);

        List<Farms> userFarms = farmRepository.findByUser(user);
        return userFarms.stream().map(this::convertFarmResponse).collect(Collectors.toList());
    }

    @Override
    public String editFarm(EditFarmRequest farmRequest, MultipartFile[] multipartFiles, Principal principal) {
        Farms farm = farmRepository.findById(farmRequest.getId());
        if (farmRequest.getName() != null) {
            farm.setName(farmRequest.getName());
        }
        if (farmRequest.getAddress() != null) {
            farm.setAddress(farmRequest.getAddress());
        }
        if (farmRequest.getLng() != 0) {
            farm.setLng(farmRequest.getLng());
        }
        if (farmRequest.getLat() != 0) {
            farm.setLat(farmRequest.getLat());
        }
        List<Images> farmImages = farm.getImages();
        for (Images image : farmImages) {
            awsutils.deleteFilefromS3(image.getImg_url());

            // imagesRepository.deleteById(image.getId());
        }
        farmImages.clear();
        // farm.getImages().clear();
        // farmImages = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            String url = awsutils.uploadFileToS3(file, "FARM", farm.getId());
            Images image = new Images();
            image.setFarm(farm);
            image.setImg_url(url);
            imagesRepository.save(image);
            System.out.println("-=-=-=-=-  " + url);
            farmImages.add(image);
        }
        farm.setImages(farmImages);
        farmRepository.save(farm);
        return "Farm details edited successfully";
    }

    @Override
    public void deleteFarm(int id) {
        Farms farm = farmRepository.findById(id);
        if (farm == null) {
            throw new ApiRequestException("Farm not found");
        }

        List<Product> products = farm.getProduct();
        // looping through products
        for (Product product : products) {

            // first deleting related images as products are associated with images
            List<Images> productImages = product.getImages();
            for (Images image : productImages) {
                imagesRepository.deleteById(image.getId());
            }
            // deleting product
            productRepository.deleteById(product.getId());
        }

        // deleting farm
        farmRepository.deleteById(id);
    }

    private FarmDto convertFarmResponse(Farms current_farm) {
        FarmDto farmDTO = new FarmDto();
        farmDTO.setId(current_farm.getId());
        farmDTO.setName(current_farm.getName());
        farmDTO.setAddress(current_farm.getAddress());
        farmDTO.setLat(current_farm.getLat());
        farmDTO.setLng(current_farm.getLng());

        for (Images images : current_farm.getImages()) {
            farmDTO.addImage(images);
        }

        return farmDTO;
    }
}

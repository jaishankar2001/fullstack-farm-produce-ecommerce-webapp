package com.example.backend.services.impl;

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
import com.example.backend.entities.User;
import com.example.backend.repository.FarmRepository;
import com.example.backend.repository.ImagesRepository;
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

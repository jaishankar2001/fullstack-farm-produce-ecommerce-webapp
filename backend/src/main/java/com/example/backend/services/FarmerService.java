package com.example.backend.services;

import com.example.backend.dto.request.EditFarmRequest;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import java.security.Principal;
import com.example.backend.dto.request.AddFarmRequest;
import com.example.backend.dto.response.FarmDto;

public interface FarmerService {
    List<FarmDto> addFarm(AddFarmRequest farmRequest, MultipartFile[] files, Principal principal);

    String editFarm(EditFarmRequest farmRequest, MultipartFile[] files, Principal principal);
}

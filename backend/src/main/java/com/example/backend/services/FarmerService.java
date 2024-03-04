package com.example.backend.services;

import com.example.backend.dto.request.EditFarmRequest;
import com.example.backend.dto.request.FarmerOwnFarmRequest;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import java.security.Principal;
import com.example.backend.dto.request.AddFarmRequest;
import com.example.backend.dto.response.FarmDto;
import com.example.backend.dto.response.GetFarmByIdResponse;
import com.example.backend.entities.Farms;

public interface FarmerService {
    List<FarmDto> addFarm(AddFarmRequest farmRequest, MultipartFile[] files, Principal principal);

    void deleteFarm(int id);

    String editFarm(EditFarmRequest farmRequest, MultipartFile[] files, Principal principal);

    List<FarmDto> getFarms(FarmerOwnFarmRequest farmerOwnFarmRequest, Principal principal);

    GetFarmByIdResponse getFarmById(int id);
}

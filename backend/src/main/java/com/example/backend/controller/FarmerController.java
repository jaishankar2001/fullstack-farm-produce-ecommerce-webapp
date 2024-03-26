package com.example.backend.controller;

import com.example.backend.dto.request.EditFarmRequest;
import com.example.backend.dto.request.FarmerOwnFarmRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.backend.dto.request.AddFarmRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.FarmDto;
import com.example.backend.dto.response.GetFarmByIdResponse;
import com.example.backend.services.FarmerService;
import java.util.*;
import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/farmer")
@RequiredArgsConstructor
public class FarmerController {

    private final FarmerService farmerService;

    @PostMapping("/own-farms")
    public ResponseEntity<List<FarmDto>> getFarms(@RequestBody FarmerOwnFarmRequest farmerOwnFarmRequest,
            Principal principal) {
        List<FarmDto> userFarms = farmerService.getFarms(farmerOwnFarmRequest, principal);
        return ResponseEntity.ok(userFarms);
    }

    @PostMapping("/addfarm")
    public ResponseEntity<Map> addFarm(@ModelAttribute AddFarmRequest farmRequest,
            @RequestParam(value = "files") MultipartFile[] files, Principal principal) {
        System.out.println("files" + files);
        List<FarmDto> AllFarmerFarms = farmerService.addFarm(farmRequest, files,
                principal);
        Map<String, Object> response = new HashMap<>();
        response.put("AllFarm", AllFarmerFarms);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/editfarm")
    public ResponseEntity<Map> editFarm(@ModelAttribute EditFarmRequest farmRequest,
            @RequestPart(value = "files", required = false) MultipartFile[] files, Principal principal) {
        String editFarmResponse = farmerService.editFarm(farmRequest, files, principal);
        Map<String, Object> response = new HashMap<>();
        response.put("message", principal.getName());
        response.put("Result", editFarmResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/farms/{id}")
    public ResponseEntity<ApiResponse> deleteFarm(@PathVariable int id) {
        farmerService.deleteFarm(id);
        ApiResponse response = new ApiResponse();
        response.setMessage("Farm deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getFarm/{farmId}")
    public ResponseEntity<GetFarmByIdResponse> getFarmById(@PathVariable int farmId) {
        GetFarmByIdResponse response = farmerService.getFarmById(farmId);
        return ResponseEntity.ok(response);
    }

}

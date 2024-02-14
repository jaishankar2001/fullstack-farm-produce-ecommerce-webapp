package com.example.backend.controller;

import com.example.backend.dto.request.EditFarmRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.dto.request.AddFarmRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.FarmDto;
import com.example.backend.entities.Farms;
import com.example.backend.services.FarmerService;

// import com.example.Auth.entities.Farms;
// import com.example.Auth.services.FarmerService;

import java.util.*;
import java.util.stream.Collectors;
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

    @GetMapping
    public ResponseEntity<String> farmerHome() {
        return ResponseEntity.ok("Hi Farmer");
    }

    @GetMapping("/own-farms")
    public ResponseEntity<List<FarmDto>> getFarms(Principal principal) {
        List<FarmDto> userFarms = farmerService.getFarms(principal);
        return ResponseEntity.ok(userFarms);
    }

    @PostMapping("/addfarm")
    public ResponseEntity<Map> addFarm(@ModelAttribute AddFarmRequest farmRequest,
            @RequestParam(value = "files") MultipartFile[] files, Principal principal) {
        System.out.println("files" + files);
        List<FarmDto> AllFarmerFarms = farmerService.addFarm(farmRequest, files,
                principal);
        Map<String, Object> response = new HashMap<>();
        response.put("Farmsss", AllFarmerFarms);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/editfarm")
    public ResponseEntity<Map> editFarm(@ModelAttribute EditFarmRequest farmRequest,
            @RequestPart(value = "files") MultipartFile[] files, Principal principal) {
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

}

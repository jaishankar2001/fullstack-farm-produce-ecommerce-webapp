package com.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.dto.request.AddFarmRequest;
import com.example.backend.dto.response.FarmDto;
import com.example.backend.services.FarmerService;

// import com.example.Auth.entities.Farms;
// import com.example.Auth.services.FarmerService;

import java.util.*;

import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @PostMapping("/addfarm")
    public ResponseEntity<Map> addFarm(@ModelAttribute AddFarmRequest farmRequest,
            @RequestPart(value = "files") MultipartFile[] files, Principal principal) {
        List<FarmDto> AllFarmerFarms = farmerService.addFarm(farmRequest, files, principal);
        Map<String, Object> response = new HashMap<>();

        response.put("message", principal.getName());
        response.put("Farmsss", AllFarmerFarms);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

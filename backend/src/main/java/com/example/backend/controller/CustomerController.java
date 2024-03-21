package com.example.backend.controller;

import com.example.backend.dto.response.FarmDto;
import com.example.backend.services.FarmerService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final FarmerService farmerService;

    @GetMapping("/listfarms")
    public ResponseEntity<List<FarmDto>> listFarms(@RequestParam("farmName") String farmName) {
        List<FarmDto> allFarms = farmerService.getAllFarms(farmName);
        return ResponseEntity.ok(allFarms);
    }
}

package com.example.backend.controller;

import com.example.backend.dto.request.ResetPasswordRequest;
import com.example.backend.dto.request.ShowFarmsRequest;
import com.example.backend.dto.response.FarmDto;
import com.example.backend.services.FarmerService;
import com.example.backend.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final FarmerService farmerService;


    @GetMapping
    public ResponseEntity<String> customerHome() {
        return ResponseEntity.ok("Hello Buyer!");
    }

    @GetMapping("/listfarms")
    public ResponseEntity<List<FarmDto>> listFarms(@RequestParam("farmName") String farmName) {
        List<FarmDto> allFarms = farmerService.getAllFarms(farmName);
        return ResponseEntity.ok(allFarms);
    }
}

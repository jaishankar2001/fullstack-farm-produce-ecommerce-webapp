package com.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.example.Auth.entities.Farms;
// import com.example.Auth.services.FarmerService;

import java.util.*;

import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/farmer")
// @RequiredArgsConstructor

public class FarmerController {
    @GetMapping
    public ResponseEntity<String> farmerHome() {
        return ResponseEntity.ok("Hi Farmer");
    }
}

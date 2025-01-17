package com.example.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.dto.response.HomeResponse;
import com.example.backend.services.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;

    @GetMapping("/")
    public ResponseEntity<HomeResponse> getHomeMeta() {
        HomeResponse homeMeta = homeService.getHomeMeta();
        return ResponseEntity.ok(homeMeta);
    }

}

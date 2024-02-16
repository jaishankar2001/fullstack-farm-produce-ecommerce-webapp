package com.example.backend.dto.request;

import lombok.Data;

@Data
public class AddFarmRequest {
    private String name;
    private String Address;
    private double lat;
    private double lng;
}

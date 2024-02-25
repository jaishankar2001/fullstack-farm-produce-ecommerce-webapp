package com.example.backend.dto.request;

import lombok.Data;

@Data
public class AddFarmRequest {
    private String name;
    private String Address;
    private String Description;
    private double lat;
    private double lng;
}

package com.example.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditFarmRequest {
    @NotNull(message = "Farm ID is required")
    private int id;
    private String name;
    private String Address;
    private String Description;
    private double lat;
    private double lng;
}

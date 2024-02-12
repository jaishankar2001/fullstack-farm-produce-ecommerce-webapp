package com.example.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class EditFarmRequest {
    @NotNull(message = "Farm ID is required")
    private int id;
    private String name;
    private String Address;
    private int lat;
    private int lng;
}

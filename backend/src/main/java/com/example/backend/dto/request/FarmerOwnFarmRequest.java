package com.example.backend.dto.request;

import io.micrometer.common.lang.Nullable;
import lombok.Data;

@Data
public class FarmerOwnFarmRequest {
    @Nullable
    private String searchTerm;
}

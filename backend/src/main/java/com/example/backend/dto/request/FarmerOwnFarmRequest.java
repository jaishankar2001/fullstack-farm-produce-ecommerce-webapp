package com.example.backend.dto.request;

import io.micrometer.common.lang.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmerOwnFarmRequest {
    @Nullable
    private String searchTerm;
}

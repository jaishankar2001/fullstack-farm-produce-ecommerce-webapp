package com.example.backend.dto.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ShowFarmsRequest {
    private String farmName;
}
package com.example.testapp.dto.provider;

import lombok.Data;

@Data
public class AllowAccessToDeleteProviderRequestDTO {
    private String nameOfHost;
    private String passwordForHost;
}

package com.example.testapp.dto.provider;

import lombok.Data;

@Data
public class AddProviderForHostRequestDTO {
    private String name;
    private String email;
    private String password;

    private String nameOfHost;
    private String passwordForHost;
}

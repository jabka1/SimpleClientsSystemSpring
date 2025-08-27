package com.example.testapp.dto.provider;

import lombok.Data;

@Data
public class PutProvidersCredentialsForAdminRequestDTO {
    private String name;
    private String email;
    private String password;

    private String nameOfHost;
    private String passwordForHost;
}

package com.example.testapp.dto.providerUser;

import lombok.Data;

@Data
public class AllowAccessToDeleteUserRequestDTO {
    private String provider;
    private String password;
}

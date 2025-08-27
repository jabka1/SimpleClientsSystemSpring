package com.example.testapp.dto.providerUser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PutUsersIMEIForProviderRequestDTO {

    private String IMEI;

    private String provider;
    private String password;
}

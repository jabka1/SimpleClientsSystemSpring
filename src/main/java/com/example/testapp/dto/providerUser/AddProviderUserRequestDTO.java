package com.example.testapp.dto.providerUser;

import lombok.Data;

@Data
public class AddProviderUserRequestDTO {
    private String MSISDN;
    private String IMEI;
    private String IMSI;

    private String provider;
    private String password;
}

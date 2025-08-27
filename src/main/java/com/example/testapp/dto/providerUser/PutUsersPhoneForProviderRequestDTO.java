package com.example.testapp.dto.providerUser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PutUsersPhoneForProviderRequestDTO {

    private String MSISDN;
    private String IMSI;

    private String provider;
    private String password;
}

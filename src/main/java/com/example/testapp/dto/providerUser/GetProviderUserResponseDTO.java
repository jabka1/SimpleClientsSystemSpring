package com.example.testapp.dto.providerUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProviderUserResponseDTO {
    private Long id;
    private String MSISDN;
    private String IMEI;
    private String IMSI;
    private String provider;
}

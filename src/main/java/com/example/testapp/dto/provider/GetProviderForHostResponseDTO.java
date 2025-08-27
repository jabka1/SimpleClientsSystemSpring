package com.example.testapp.dto.provider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProviderForHostResponseDTO {
    private Long id;
    private String name;
    private String email;
}

package com.example.testapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProviderUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 12)
    private String MSISDN;

    @Column(unique = true, nullable = false, length = 15)
    private String IMEI;

    @Column(unique = true, nullable = false, length = 15)
    private String IMSI;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
}

package com.example.testapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 8, max = 25)
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Host host;

    /*@OneToMany
    private List<ProviderUser> providerUser;*/
}

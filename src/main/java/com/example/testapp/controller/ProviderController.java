package com.example.testapp.controller;

import com.example.testapp.dto.provider.AddProviderForHostRequestDTO;
import com.example.testapp.dto.provider.AllowAccessToDeleteProviderRequestDTO;
import com.example.testapp.dto.provider.GetProviderForHostResponseDTO;
import com.example.testapp.dto.provider.PutProvidersCredentialsForAdminRequestDTO;
import com.example.testapp.service.ProviderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provider")
public class ProviderController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping
    public ResponseEntity<String> createProvider(@RequestBody AddProviderForHostRequestDTO addProviderForHostRequestDTO) {
        try {
            providerService.createProvider(addProviderForHostRequestDTO);
            return ResponseEntity.ok().body("success");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<GetProviderForHostResponseDTO>> getAllProviders() {
        return ResponseEntity.ok().body(providerService.getProviders());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetProviderForHostResponseDTO> getProvider(@PathVariable Long id) {
        return ResponseEntity.ok().body(providerService.getProvider(id));
    }

    @DeleteMapping("/delete-provider/{id}")
    public ResponseEntity<String> deleteProvider(@PathVariable Long id, @RequestBody AllowAccessToDeleteProviderRequestDTO allowAccessToDeleteProviderRequestDTO) {
        try{
            providerService.deleteProvider(id, allowAccessToDeleteProviderRequestDTO);
            return ResponseEntity.ok().body("success");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/change-credentials-provider/{id}")
    public ResponseEntity<String> changeProviderCredentials(@PathVariable Long id, @RequestBody PutProvidersCredentialsForAdminRequestDTO putProvidersCredentialsForAdminRequestDTO) {
        try{
            providerService.changeProviderCredentials(id, putProvidersCredentialsForAdminRequestDTO);
            return ResponseEntity.ok().body("success");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
package com.example.testapp.controller;

import com.example.testapp.dto.providerUser.*;
import com.example.testapp.service.ProviderUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers-users")
public class ProviderUserController {

    private final ProviderUserService providerUserService;

    ProviderUserController(ProviderUserService providerUserService) {
    this.providerUserService = providerUserService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody AddProviderUserRequestDTO providerUserDTO) {
        try{
            providerUserService.createProviderUser(providerUserDTO);
            return ResponseEntity.ok("success");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<GetProviderUserResponseDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(providerUserService.getProviderUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<GetProviderUserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok().body(providerUserService.getAllProviderUsers());
    }

    @GetMapping("/provider/{id}")
    public ResponseEntity<List<GetProviderUserResponseDTO>> getUsersByProvider(@PathVariable Long id) {
        return ResponseEntity.ok().body(providerUserService.getAllUsersByProvider(id));
    }

    @PutMapping("/change-phone-for-user/{id}")
    public ResponseEntity<String> changePhoneForUser(@PathVariable Long id, @RequestBody PutUsersPhoneForProviderRequestDTO putUsersPhoneForProviderRequestDTO) {
        try{
            providerUserService.putUsersPhoneNumber(id, putUsersPhoneForProviderRequestDTO);
            return ResponseEntity.ok("success");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/change-IMEI-for-user/{id}")
    public ResponseEntity<String> changeIMEIforUser(@PathVariable Long id, @RequestBody PutUsersIMEIForProviderRequestDTO putUsersIMEIForProviderRequestDTO){
        try {
            providerUserService.putUsersIMEI(id, putUsersIMEIForProviderRequestDTO);
            return ResponseEntity.ok("success");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public  ResponseEntity<String> deleteUser(@PathVariable Long id, @RequestBody AllowAccessToDeleteUserRequestDTO allowAccessToDeleteUserRequestDTO) {
        try{
            providerUserService.deleteUser(id, allowAccessToDeleteUserRequestDTO);
            return ResponseEntity.ok("success");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

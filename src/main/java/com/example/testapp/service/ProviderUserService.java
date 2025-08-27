package com.example.testapp.service;

import com.example.testapp.dto.providerUser.*;
import com.example.testapp.model.Provider;
import com.example.testapp.model.ProviderUser;
import com.example.testapp.repository.ProviderRepository;
import com.example.testapp.repository.ProviderUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderUserService {
    private final ProviderUserRepository providerUserRepository;
    private final ProviderRepository providerRepository;
    private static final Logger log = LoggerFactory.getLogger(ProviderUserService.class);

    ProviderUserService(ProviderUserRepository providerUserRepository, ProviderRepository providerRepository) {
        this.providerUserRepository = providerUserRepository;
        this.providerRepository = providerRepository;
    }

    public void createProviderUser(AddProviderUserRequestDTO providerUserDTO) {
        Provider provider = providerRepository.findByName(providerUserDTO.getProvider());

        if(!provider.getPassword().equals(providerUserDTO.getPassword())){
            log.info("---Provider " + provider.getName() + " enter wrong password---\n");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong Password!");
        }
        else {
            ProviderUser providerUser = new ProviderUser();
            providerUser.setMSISDN(providerUserDTO.getMSISDN());
            providerUser.setIMEI(providerUserDTO.getIMEI());
            providerUser.setIMSI(providerUserDTO.getIMSI());
            providerUser.setProvider(provider);
            providerUserRepository.save(providerUser);
            log.info("---Provider " + provider.getName() + " successfully created a client---\n");
        }
    }

    public GetProviderUserResponseDTO getProviderUserById(Long id) {
        ProviderUser providerUser = providerUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        GetProviderUserResponseDTO getProviderUserResponseDTO = new GetProviderUserResponseDTO();
        getProviderUserResponseDTO.setId(providerUser.getId());
        getProviderUserResponseDTO.setMSISDN(providerUser.getMSISDN());
        getProviderUserResponseDTO.setIMEI(providerUser.getIMEI());
        getProviderUserResponseDTO.setIMSI(providerUser.getIMSI());
        getProviderUserResponseDTO.setProvider(providerUser.getProvider().getName());
        log.info("---Provider successfully received client data---\n");
        return getProviderUserResponseDTO;
    }

    public List<GetProviderUserResponseDTO> getAllProviderUsers() {
        log.info("---Provider successfully received a general list of clients---\n");
        return providerUserRepository.findAll()
                .stream().map(user -> new GetProviderUserResponseDTO(user.getId(), user.getMSISDN(), user.getIMEI(), user.getIMSI(), user.getProvider().getName()))
                .toList();
    }

    public List<GetProviderUserResponseDTO> getAllUsersByProvider(Long id) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));
        log.info("---Provider successfully received a list of clients - " + provider.getName() + "---\n");
        return providerUserRepository.findAll()
                .stream().map(user -> new GetProviderUserResponseDTO(user.getId(), user.getMSISDN(), user.getIMEI(), user.getIMSI(), user.getProvider().getName()))
                .filter(user -> provider.getName().equals(user.getProvider()))
                .toList();
    }

    public void putUsersPhoneNumber(Long id, PutUsersPhoneForProviderRequestDTO putUsersPhoneForProviderRequestDTO) {
        Provider provider = providerRepository.findByName(putUsersPhoneForProviderRequestDTO.getProvider());

        if(!provider.getPassword().equals(putUsersPhoneForProviderRequestDTO.getPassword())){
            log.info("---Provider " + provider.getName() + " enter wrong password---\n");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong Password!");
        }
        else {
            ProviderUser providerUser = providerUserRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            providerUser.setMSISDN(putUsersPhoneForProviderRequestDTO.getMSISDN());
            providerUser.setIMSI(putUsersPhoneForProviderRequestDTO.getIMSI());
            providerUserRepository.save(providerUser);
            log.info("---Provider successfully changed clients phone number---\n");
        }
    }

    public void putUsersIMEI(Long id, PutUsersIMEIForProviderRequestDTO  putUsersIMEIForProviderRequestDTO) {
        Provider provider = providerRepository.findByName(putUsersIMEIForProviderRequestDTO.getProvider());

        if(!provider.getPassword().equals(putUsersIMEIForProviderRequestDTO.getPassword())){
            log.info("---Provider " + provider.getName() + " enter wrong password---\n");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong Password!");
        }
        else{
            ProviderUser providerUser = providerUserRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            providerUser.setIMEI(putUsersIMEIForProviderRequestDTO.getIMEI());
            providerUserRepository.save(providerUser);
            log.info("---Provider successfully changed clients IMEI---\n");
        }
    }

    public void deleteUser(Long id, AllowAccessToDeleteUserRequestDTO allowAccessToDeleteUserRequestDTO) {
        Provider provider = providerRepository.findByName(allowAccessToDeleteUserRequestDTO.getProvider());

        if(!provider.getPassword().equals(allowAccessToDeleteUserRequestDTO.getPassword())){
            log.info("---Provider " + provider.getName() + " enter wrong password---\n");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong Password!");
        }
        else {
            ProviderUser providerUser = providerUserRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            providerUserRepository.delete(providerUser);
            log.info("---Provider successfully deleted client---\n");
        }
    }
}

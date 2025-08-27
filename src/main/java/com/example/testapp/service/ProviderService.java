package com.example.testapp.service;

import com.example.testapp.dto.provider.AddProviderForHostRequestDTO;
import com.example.testapp.dto.provider.AllowAccessToDeleteProviderRequestDTO;
import com.example.testapp.dto.provider.GetProviderForHostResponseDTO;
import com.example.testapp.dto.provider.PutProvidersCredentialsForAdminRequestDTO;
import com.example.testapp.model.Host;
import com.example.testapp.model.Provider;
import com.example.testapp.repository.HostRepository;
import com.example.testapp.repository.ProviderRepository;
import com.example.testapp.repository.ProviderUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ProviderService {
    private final ProviderRepository providerRepository;
    private final HostRepository hostRepository;
    private static final Logger log = LoggerFactory.getLogger(ProviderService.class);

    ProviderService(ProviderRepository providerRepository, HostRepository hostRepository) {
        this.providerRepository = providerRepository;
        this.hostRepository = hostRepository;
    }

    public void createProvider(AddProviderForHostRequestDTO addProviderForHostRequestDTO){
        Host host = hostRepository.findFirstBy();

        if(!host.getPassword().equals(addProviderForHostRequestDTO.getPasswordForHost())
                || !host.getName().equals(addProviderForHostRequestDTO.getNameOfHost())){
            log.info("---Host enter wrong password---\n");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong Password!");
        }
        else {
            Provider provider = new Provider();
            provider.setName(addProviderForHostRequestDTO.getName());
            provider.setPassword(addProviderForHostRequestDTO.getPassword());
            provider.setEmail(addProviderForHostRequestDTO.getEmail());
            provider.setHost(host);
            host.getProviders().add(provider);
            providerRepository.save(provider);
            log.info("---Provider was successfully created - {" + provider.getName() + "}---\n");
        }
    }

    public List<GetProviderForHostResponseDTO> getProviders() {
        log.info("---Host received a list of providers---\n");
        return providerRepository.findAll().stream()
                .map(provider -> new GetProviderForHostResponseDTO(provider.getId(), provider.getName(), provider.getEmail()))
                .toList();
    }

    public GetProviderForHostResponseDTO getProvider(Long id) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provider Not Found!"));

        GetProviderForHostResponseDTO getProviderForHostResponseDTO = new GetProviderForHostResponseDTO();
        getProviderForHostResponseDTO.setId(provider.getId());
        getProviderForHostResponseDTO.setName(provider.getName());
        getProviderForHostResponseDTO.setEmail(provider.getEmail());
        log.info("---Host received provider data - {" + provider.getName() + "\n");
        return  getProviderForHostResponseDTO;
    }

    public void deleteProvider(Long id, AllowAccessToDeleteProviderRequestDTO allowAccessToDeleteProviderRequestDTO) {
        Host host = hostRepository.findFirstBy();

        if(!host.getPassword().equals(allowAccessToDeleteProviderRequestDTO.getPasswordForHost())
                || !host.getName().equals(allowAccessToDeleteProviderRequestDTO.getNameOfHost())){
            log.info("---Host enter wrong password---\n");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong Password!");
        }
        else{
            Provider provider = providerRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Provider Not Found!"));
            providerRepository.delete(provider);
            log.info("---Host delete provider - {" + provider.getName() + "\n");
        }
    }

    public void changeProviderCredentials(Long id, PutProvidersCredentialsForAdminRequestDTO putProvidersCredentialsForAdminRequestDTO) {
        Host host = hostRepository.findFirstBy();

        if(!host.getPassword().equals(putProvidersCredentialsForAdminRequestDTO.getPasswordForHost())
                || !host.getName().equals(putProvidersCredentialsForAdminRequestDTO.getNameOfHost())){
            log.info("---Host enter wrong password---\n");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong Password!");
        }
        else {
            Provider provider = providerRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Provider Not Found!"));
            log.info("---Host changed provider data - {" + provider.getName() + "}---\n");
            provider.setName(putProvidersCredentialsForAdminRequestDTO.getName());
            provider.setEmail(putProvidersCredentialsForAdminRequestDTO.getEmail());
            provider.setPassword(putProvidersCredentialsForAdminRequestDTO.getPassword());
            providerRepository.save(provider);
        }
    }
}

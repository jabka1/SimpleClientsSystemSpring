package com.example.testapp.hostInitializer;

import com.example.testapp.model.Host;
import com.example.testapp.repository.HostRepository;
import com.example.testapp.repository.ProviderRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/*
* Resource: https://stackoverflow.com/questions/27405713/running-code-after-spring-boot-starts
*/

@Component
public class HostInitializer {

    private final HostRepository hostRepository;
    private final ProviderRepository providerRepository;

    HostInitializer(HostRepository hostRepository, ProviderRepository providerRepository) {
        this.hostRepository = hostRepository;
        this.providerRepository = providerRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {
        if (hostRepository.findAll().isEmpty()) {
            Host host = new Host();
            host.setName("host");
            host.setPassword("1234");
            host.setEmail("hostOfService@email.com");
            host.setProviders(providerRepository.findAll());
            hostRepository.save(host);
        }
    }
}
package ru.netology.secure_application.service;

import org.springframework.stereotype.Service;
import ru.netology.secure_application.repository.ApplicationRepository;

import java.security.Principal;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public String getHomePage() {
        return applicationRepository.getHomePage();
    }

    public String getAuthenticatedPage(Principal principal) {
        return applicationRepository.getAuthenticatedPage(principal);
    }

    public String getSupervisorPage(Principal principal) {
        return applicationRepository.getSupervisorPage(principal);
    }


}
package ru.netology.secure_application.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.secure_application.service.ApplicationService;

import java.security.Principal;
@RestController
// @SpringBootApplication
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/")
    public String homePage() {
        return applicationService.getHomePage();
    }

    @GetMapping("/authenticated")
    public String authenticatedPage(Principal principal) {
        return applicationService.getAuthenticatedPage(principal);
    }

    @GetMapping("/supervisor")
    public String supervisorPage(Principal principal) {
        return applicationService.getSupervisorPage(principal);
    }

}

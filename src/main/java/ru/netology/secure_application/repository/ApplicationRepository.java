package ru.netology.secure_application.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.Principal;

@Repository
public class ApplicationRepository {


    @Autowired
    public ApplicationRepository() {
    }

    public String getHomePage() {
        return "Здравствуйте, вы находитесь на домашней странице нашего сайта";
    }

    public String getAuthenticatedPage(Principal principal) {
        return "Это страница для авторизованных пользователей, вы вошли как: " + principal.getName();
    }

    public String getSupervisorPage(Principal principal) {
        return "Это страница для Администраторов, вы Администратор: " + principal.getName();
    }



}


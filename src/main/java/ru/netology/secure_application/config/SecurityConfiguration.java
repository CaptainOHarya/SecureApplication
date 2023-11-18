package ru.netology.secure_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
//@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Supervisor")
                .password(encoder().encode("{bcrypt} $2a$12$HcyO/lbwz3rZTvR.FHjx7OIGX4v8BGHO5t8VYr.KFAx6/yz7ZnXAG"))
                .authorities("authenticated", "supervisor")
                .and()
                .withUser("Guest")
                .password(encoder().encode("{bcrypt}$2a$12$7PmTgKEWz3Vt.qD8APdv1e.qGTqh54SMotDGzdsOJDkW0npLdZamW"))
                .authorities("authenticated")
                .and()
                .withUser("Katerina")
                .password(encoder().encode("{bcrypt}$2a$12$T1gJ7vUu2zThhW9HQb4gEOlN/7sCnTBw0e2vOGzrgl7xOtCH6AK9."))
                .authorities("authenticated", "supervisor")
                .and()
                .withUser("Marina")
                .password(encoder().encode("{bcrypt}$2a$12$Qim2vho7gEYhCav2kystbe6bpdI.gyVZCCyJf3PFxZFiON5EeGWUy"))
                .authorities("authenticated");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET,"/supervisor").hasAuthority("supervisor")
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }

}

package ru.netology.secure_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
//@EnableWebSecurity
// @ComponentScan(basePackages =  "ru.netology.secure_application")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Supervisor")
                .password(encoder().encode("supervisor"))
                .authorities("authenticated", "supervisor")
                .and()
                .withUser("Guest")
                .password(encoder().encode("guest"))
                .authorities("authenticated")
                .and()
                .withUser("Katerina")
                .password(encoder().encode("katerina"))
                .authorities("authenticated", "supervisor")
                .and()
                .withUser("Marina")
                .password(encoder().encode("marina"))
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

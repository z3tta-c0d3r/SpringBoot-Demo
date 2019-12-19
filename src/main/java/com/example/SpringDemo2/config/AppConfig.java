package com.example.SpringDemo2.config;

import com.example.SpringDemo2.model.User;
import com.example.SpringDemo2.model.UserRole;
import com.example.SpringDemo2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.example.SpringDemo2"})
@AllArgsConstructor
public class AppConfig {

    private final UserRepository userRepository;

    /**
     * Initialize user for http://localhost:8080/oauth/token
     */
    @Bean
    public void initUser(){
       User user =  User.builder()
               .username("crmadmin")
               .password(encoder().encode("adminpass"))
               .roles(Arrays.asList(new UserRole("ADMIN")))
               .build();

        if (userRepository.count() == 0) {
            userRepository.save(user);
        }

    }

    /**
     * Password encrypt
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
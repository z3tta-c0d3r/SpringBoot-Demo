package com.example.SpringDemo2.config;

import com.example.SpringDemo2.model.User;
import com.example.SpringDemo2.model.UserRole;
import com.example.SpringDemo2.repository.UserRepository;
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
public class AppConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void initUser(){
       User user =  User.builder()
               .username("crmadmin")
               .password(encoder().encode("adminpass"))
               .roles(Arrays.asList(new UserRole("USER"), new UserRole("ADMIN")))
               .build();

        if (userRepository.count() == 0) {
            userRepository.save(user);
        }

    }

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
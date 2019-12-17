package com.example.SpringDemo2.service;

import com.example.SpringDemo2.model.User;
import com.example.SpringDemo2.model.UserRole;
import com.example.SpringDemo2.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Service(value = "UserService")
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAuthority('Admin')")
    //@PreAuthorize("isAnonymous()")
    //@PreAuthorize("hasPermission(#user, 'admin')")
    //@PreAuthorize("isAuthenticated()")
    public User addUser(@NotNull User user) {
        String pwdencode = encoder().encode(user.getPassword());
        user.setPassword(pwdencode);
        return userRepository.save(user);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *
     * set up a default user with two roles USER and ADMIN
     *
     */
    @PostConstruct
    private void setupDefaultUser() {
        //-- just to make sure there is an ADMIN user exist in the database for testing purpose
        if (userRepository.count() == 0) {
            /*User user = User.builder()
                    .username("crmadmin")
                    .password(encoder().encode("adminpass"))
                    .roles(Arrays.asList(new UserRole("USER"), new UserRole("ADMIN")))
                    .build();

             */
            //userRepository.save(user);
        }
    }


}

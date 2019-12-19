package com.example.SpringDemo2.service;

import com.example.SpringDemo2.model.User;
import com.example.SpringDemo2.model.UserRole;
import com.example.SpringDemo2.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Add user into BD
     * @param user User
     * @return User
     */
    public User addUser(@NotNull User user) {
        String pwdencode = encoder().encode(user.getPassword());
        user.setPassword(pwdencode);
        return userRepository.save(user);
    }

    /**
     *
     * @return
     */
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}

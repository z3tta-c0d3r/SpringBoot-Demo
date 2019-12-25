package com.example.SpringDemo2.service;

import com.example.SpringDemo2.model.User;
import com.example.SpringDemo2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "UserService")
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    /**
     * Add user into BD
     * @param user User
     * @return User
     */
    @Override
    public User addUser(@NotNull User user) {
        String pwdencode = encoder().encode(user.getPassword());
        user.setPassword(pwdencode);
        return userRepository.save(user);
    }

    /**
     * Find all users in application
     * @return
     */
    @Override
    public List<User> findAll() {
        List<User> listUser = new ArrayList<>();

        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        List<User> listUser = new ArrayList<>();

        return userRepository.findById(id);
    }

    /**
     * Password encoder BCrypt
     * @return
     */
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}

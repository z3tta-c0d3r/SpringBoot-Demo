package com.example.SpringDemo2.service;

import com.example.SpringDemo2.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    User addUser(@NotNull User user);
    List<User> findAll();
    Optional<User> findById(Long id);
}

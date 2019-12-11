package com.example.SpringDemo2.repository;

import com.example.SpringDemo2.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
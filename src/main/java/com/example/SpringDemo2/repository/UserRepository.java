package com.example.SpringDemo2.repository;

import com.example.SpringDemo2.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> findById(String id);
}
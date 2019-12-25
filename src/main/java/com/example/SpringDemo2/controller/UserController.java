package com.example.SpringDemo2.controller;

import com.example.SpringDemo2.model.User;
import com.example.SpringDemo2.model.UserRole;
import com.example.SpringDemo2.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    /**
     * Create one user in DB
     * @param user User
     * @return ResponseEntity<?>
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        user.setRoles(Arrays.asList(new UserRole("USER")));
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Create one user in DB
     * @return ResponseEntity<?>
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/users")
    public ResponseEntity<?> users() {
        List<User> listUser = userService.findAll();
        return new ResponseEntity<>(listUser, HttpStatus.CREATED);
    }

    /**
     * Create one user in DB
     * @return ResponseEntity<?>
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable("id") long id) {
        Optional<User> listUser = userService.findById(id);
        return new ResponseEntity<>(listUser.get(), HttpStatus.CREATED);
    }

}

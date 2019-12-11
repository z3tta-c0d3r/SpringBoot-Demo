package com.example.SpringDemo2.controller;

import com.example.SpringDemo2.model.User;
import com.example.SpringDemo2.model.UserRole;
import com.example.SpringDemo2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * user signup
     * @param user
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody User user) {
        user.setRoles(Arrays.asList(new UserRole("USER")));
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

package com.example.SpringDemo2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "Hello World";
    }

    @RequestMapping("/testroleadmin")
    public String testpreauthorize(){return "Hello World Preauthorize ROLE ADMIN";}

    @GetMapping("/testroleuser")
    public String testpreauthorizeuser(){return "Hello World Preauthorize ROLE USER";}
}
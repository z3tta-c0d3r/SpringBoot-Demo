package com.example.SpringDemo2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
@Api(value = "Test System",tags = {"Test System Tag"})
public class TestController {

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Test", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public String test() {
        return "Hello World";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //@GetMapping("/testroleadmin")
    @GetMapping(value = "/testroleadmin", produces = MediaType.APPLICATION_JSON_VALUE)
    public String testpreauthorize(){return "Hello World Preauthorize ROLE ADMIN";}

    @PreAuthorize("hasRole('ROLE_USER')")
    //@GetMapping("/testroleuser")
    @GetMapping(value = "/testroleuser", produces = MediaType.APPLICATION_JSON_VALUE)
    public String testpreauthorizeuser(){return "Hello World Preauthorize ROLE USER";}
}
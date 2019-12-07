package com.example.SpringDemo2.web;

import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@Validated
public class DemoController {

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome security";
    }

    @GetMapping({"/getNow"})
    public String getNow(Model model) {
        return "welcome security - > " + LocalDate.now();
    }

    @GetMapping({"/getTime"})
    public String getTime(Model model) {
        return "welcome security -> " + LocalTime.now();
    }
}

package com.example.SpringDemo2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.example.SpringDemo2", exclude = {
		WebMvcAutoConfiguration.class })
public class SpringDemo2Application {

	public static void main(String[] args) {

		SpringApplication.run(SpringDemo2Application.class, args);
	}
}

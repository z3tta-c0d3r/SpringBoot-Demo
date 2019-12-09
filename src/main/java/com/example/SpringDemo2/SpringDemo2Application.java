package com.example.SpringDemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class SpringDemo2Application {

	public static void main(String[] args) {

		SpringApplication.run(SpringDemo2Application.class, args);
	}

}

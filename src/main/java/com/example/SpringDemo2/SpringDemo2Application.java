package com.example.SpringDemo2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@EnableResourceServer
@EnableAuthorizationServer
@SpringBootApplication(scanBasePackages = "com.example.SpringDemo2", exclude = {
		WebMvcAutoConfiguration.class })
@Slf4j
public class SpringDemo2Application {

	public static void main(String[] args) {

		SpringApplication.run(SpringDemo2Application.class, args);
	}


	//This method will be used to check if the user has a valid token to access the resource
	@RequestMapping("/validateUser")
	public Principal user(Principal user) {
		log.debug("Validateuser1: " + user.getName());
		log.debug("validateuser2: " + user.toString());
		return user;
	}

	/*
	@Configuration
	protected static class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth
					.inMemoryAuthentication()
					.withUser("javainuse-user")
					.password("{noop}javainuse-pass")
					.roles("USER");
		}

	}
	 */

}

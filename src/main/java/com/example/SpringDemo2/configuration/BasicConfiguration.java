package com.example.SpringDemo2.configuration;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("{noop}user")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}admin")
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/").hasRole("USER")
                .antMatchers("/getNow").hasAnyRole("USER")
                .antMatchers("getTime").hasAnyRole("ADMIN")
                .and().formLogin()
                .permitAll().and().httpBasic()
                .and().csrf().disable();


       /* http
                .authorizeRequests().antMatchers("/**").permitAll().antMatchers("/welcome")
                .hasAnyRole( "ADMIN").antMatchers("/getNow")
                .hasAnyRole("ADMIN").antMatchers("/getTime")
                .hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin()
                .permitAll().and().logout().permitAll();

        http.csrf().disable(); */


    }
}
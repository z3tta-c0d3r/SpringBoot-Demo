package com.example.SpringDemo2;

import com.example.SpringDemo2.config.oauth2.SecurityConfig;
import com.example.SpringDemo2.model.User;
import com.example.SpringDemo2.model.UserRole;
import com.example.SpringDemo2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SecurityConfig.class)
@WebAppConfiguration
@Slf4j
@Configuration
@ComponentScan("com.example.SpringDemo2")
@EnableWebMvc
@Import({ SecurityConfig.class })
class SpringDemo2ApplicationTests {

	//@InjectMocks
	@Autowired
	private UserService userService;

	@Test
	@WithMockUser(roles={"ADMIN"})
	public void testPreAuthorizedAdmin() {
		User user = User.builder()
				.id(new Long(1L))
				.username("testuser1")
				.password("testuser2")
				.roles(Arrays.asList(new UserRole("USER")))
				.build();

		assertNotNull(user);
		log.debug("user object: " + user.getUsername());

		//userService.addUser(user);


	}

}

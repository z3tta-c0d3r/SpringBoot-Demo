package com.example.SpringDemo2;

import com.example.SpringDemo2.model.User;
import com.example.SpringDemo2.model.UserRole;
import com.example.SpringDemo2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@Slf4j
class SpringDemo2ApplicationTests {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private UserService userService;

	@Autowired
	public UserDetailsService userDetailsService;

	@Test
	@WithMockUser(authorities="ADMIN")
	public void testPreAuthorizedAdmin() {
		User user = new User();
		user.setId(new Long(1L));
		user.setUsername("testuser1");
		user.setPassword("testuser2");
		user.setRoles(Arrays.asList(new UserRole("USER")));
		//User newUser = userService.addUser(user);
		//log.debug(newUser.getUsername());
	}

}

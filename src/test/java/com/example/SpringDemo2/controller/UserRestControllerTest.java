package com.example.SpringDemo2.controller;

import com.example.SpringDemo2.model.User;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
@AutoConfigureMockMvc
@Slf4j
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectmapper;

    @Value("${spring.data.rest.base-path}")
    String apiRootPath;

    @Test
    public void testGetSpain() throws Exception {
        String response = mockMvc.perform(get(apiRootPath +"/users/{id}/", 2))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.name", is("Spain")))
                .andReturn().getResponse().getContentAsString();

        log.info("response: " + response);
    }

    @Test
    public void testAddUser() throws Exception {
        User user = User.builder().id(Long.valueOf("1"))
                .username("mockuser").password("mockpass").build();

        String response = mockMvc
                .perform(post("/signup")
                        .content(objectmapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.CREATED.value())).andReturn().getResponse()
                .getContentAsString();

        log.info(response);
    }

    @Test
    public void testAddDuplicateUser() throws Exception {
        User user = User.builder().id(Long.valueOf("1"))
                .username("mockuser").password("mockpass").build();

        String response = mockMvc
                .perform(post("/signup")
                        .content(objectmapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value())).andReturn()
                .getResponse().getContentAsString();

        log.info(response);
    }

    @Test
    public void testRestAssured() {
        RestAssuredMockMvc.mockMvc(mockMvc);

        when().get(apiRootPath + "/user/{id}", 2)
                .then().statusCode(HttpStatus.CREATED.value());
                //.body("", equalTo(""));
    }

}

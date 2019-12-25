package com.example.SpringDemo2.service;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
class IUserServiceTest {

    @Autowired
    UserService userservice;

    @Test
    public void test() {
        assertEquals(3, userservice.findAll().size());
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


}
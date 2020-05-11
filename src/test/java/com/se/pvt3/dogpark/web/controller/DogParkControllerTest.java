package com.se.pvt3.dogpark.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class DogParkControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getDogParkById() {
    }

    @Test
    void createDogPark() {
    }

    @Test
    void handleUpdate() {
    }

    @Test
    void deleteDogPark() {
    }
}
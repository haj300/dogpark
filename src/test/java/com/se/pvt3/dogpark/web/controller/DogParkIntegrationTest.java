package com.se.pvt3.dogpark.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pvt3.dogpark.repository.DogParkRepository;
import com.se.pvt3.dogpark.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class DogParkIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    DogParkRepository dogParkRepository;

    @Autowired
    ObjectMapper objectMapper;


}

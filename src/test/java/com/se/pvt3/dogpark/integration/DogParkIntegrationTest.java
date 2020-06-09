package com.se.pvt3.dogpark.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pvt3.dogpark.repository.DogParkRepository;
import com.se.pvt3.dogpark.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @BeforeEach
    void setUp() {
        dogParkRepository.deleteAll();
    }


    //TODO

}

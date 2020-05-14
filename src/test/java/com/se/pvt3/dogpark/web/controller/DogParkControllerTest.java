package com.se.pvt3.dogpark.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pvt3.dogpark.services.DogParkService;
import com.se.pvt3.dogpark.web.model.DogParkDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
// @ExtendWith(MockitoExtension.class)
class DogParkControllerTest {

    @MockBean
    DogParkService dogParkService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    DogParkDto validDogPark;

    @BeforeEach
    void setUp() {
        validDogPark = DogParkDto.builder()
                .id(1)
                .name("hahjsh")
                .description("jhfdjgd")
                .latitude(737373)
                .longitude(647364)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getDogParkById() throws Exception {
        given(dogParkService.getDogParkById(any(Integer.class))).willReturn(java.util.Optional.ofNullable(validDogPark));

        mockMvc.perform(get("/api/v1/dog_park/" + validDogPark.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validDogPark.getId())))
                .andExpect(jsonPath("$.name", is("hahjsh")));
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
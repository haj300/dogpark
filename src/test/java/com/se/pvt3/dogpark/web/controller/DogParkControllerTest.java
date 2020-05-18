package com.se.pvt3.dogpark.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pvt3.dogpark.services.DogParkService;
import com.se.pvt3.dogpark.web.dto.DogParkRequestDto;
import com.se.pvt3.dogpark.web.dto.DogParkResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
@ExtendWith(MockitoExtension.class)
class DogParkControllerTest {

    @MockBean
    DogParkService dogParkService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    DogParkResponseDto validDogParkResponseDto;
    DogParkRequestDto validDogParkRequestDto;

    @BeforeEach
    void setUp() {
        validDogParkResponseDto = DogParkResponseDto.builder()
                .id(1)
                .name("hahjsh")
                .description("jhfdjgd")
                .latitude(737373)
                .longitude(647364)
                .build();

        validDogParkRequestDto = DogParkRequestDto.builder()
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
        doReturn(java.util.Optional.ofNullable(validDogParkResponseDto)).when(dogParkService).getDogParkById(any(Integer.class));

        mockMvc.perform(get("/api/v1/dog_park/id/" + validDogParkResponseDto.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(validDogParkResponseDto.getId()))
                .andExpect(jsonPath("$.name").value(validDogParkResponseDto.getName()));
    }

    @Test
    void getDogParkByName() throws Exception {
        doReturn(java.util.Optional.ofNullable(validDogParkResponseDto)).when(dogParkService).getDogParkByName(any(String.class));

        mockMvc.perform(get("/api/v1/dog_park/name/" + validDogParkResponseDto.getName())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(validDogParkResponseDto.getId()))
                .andExpect(jsonPath("$.name").value(validDogParkResponseDto.getName()));
    }

    @Test
    void updateDogParkById() throws Exception {
        mockMvc.perform(put("/api/v1/dog_park/update/" + validDogParkResponseDto.getId())
                .content(objectMapper.writeValueAsString(validDogParkRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(dogParkService).updateDogPark(eq(validDogParkResponseDto.getId()), eq(validDogParkRequestDto));
    }

    @Test
    void createDogPark() {
    }

    @Test
    void deleteDogPark() {
    }
}
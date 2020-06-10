package com.se.pvt3.dogpark.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pvt3.dogpark.repository.DogPark;
import com.se.pvt3.dogpark.repository.DogParkRepository;
import com.se.pvt3.dogpark.repository.ReviewRepository;
import com.se.pvt3.dogpark.web.dto.DogParkRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        reviewRepository.deleteAll();
    }

    @Test
    void shouldGetAllDogParks() throws Exception {
        DogPark dogPark = DogPark.builder()
                .latitude(124.9)
                .longitude(45.8)
                .name("Ripleys park")
                .description("Agility")
                .build();

        dogParkRepository.save(dogPark);

        mockMvc.perform(get("/api/v1/dog_park/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(dogPark.getName()))
                .andExpect(jsonPath("$[0].description").value(dogPark.getDescription()))
                .andExpect(jsonPath("$[0].id").value(dogPark.getId()));
    }

    @Test
    void shouldGetDogParkById() throws Exception {
        DogPark dogPark = DogPark.builder()
                .latitude(124.9)
                .longitude(45.8)
                .name("Ripleys park")
                .description("Agility")
                .build();

        dogParkRepository.save(dogPark);

        mockMvc.perform(get("/api/v1/dog_park/id/" + dogPark.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(dogPark.getName()))
                .andExpect(jsonPath("$.description").value(dogPark.getDescription()))
                .andExpect(jsonPath("$.id").value(dogPark.getId()));
    }

    @Test
    void shouldGetDogParkByName() throws Exception {
        DogPark dogPark = DogPark.builder()
                .latitude(124.9)
                .longitude(45.8)
                .name("Ripleys park")
                .description("Agility")
                .build();

        dogParkRepository.save(dogPark);

        mockMvc.perform(get("/api/v1/dog_park/name/" + dogPark.getName())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(dogPark.getName()))
                .andExpect(jsonPath("$.description").value(dogPark.getDescription()))
                .andExpect(jsonPath("$.id").value(dogPark.getId()));
    }


    @Test
    void shouldCreateDogPark() throws Exception {
        DogParkRequestDto dogParkRequestDto = DogParkRequestDto.builder()
                .description("En sån")
                .latitude(23.76)
                .longitude(234.65)
                .name("Stora Parken")
                .build();

        mockMvc.perform(post("/api/v1/dog_park")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(dogParkRequestDto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}

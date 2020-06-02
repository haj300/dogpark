package com.se.pvt3.dogpark.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pvt3.dogpark.repository.DogPark;
import com.se.pvt3.dogpark.repository.DogParkRepository;
import com.se.pvt3.dogpark.repository.Review;
import com.se.pvt3.dogpark.repository.ReviewRepository;
import com.se.pvt3.dogpark.web.dto.DogParkRequestDto;
import com.se.pvt3.dogpark.web.dto.ReviewRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ReviewIntegrationTests {

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
        reviewRepository.deleteAll();
        dogParkRepository.deleteAll();
    }

    @Test
    public void shouldGetAllReviews() throws Exception {
        DogPark dogPark = DogPark.builder()
                .latitude(124.9)
                .longitude(45.8)
                .name("Ripleys park")
                .description("Agility")
                .build();
        Review review = Review.builder()
                .comment("topp")
                .rating(3)
                .dogpark(dogPark)
                .build();

        dogParkRepository.save(dogPark);
        reviewRepository.save(review);

        mockMvc.perform(get("/api/v1/review/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].comment").value(review.getComment()))
                .andExpect(jsonPath("$[0].rating").value(review.getRating()))
                .andExpect(jsonPath("$[0].id").value(review.getId()));
    }

    @Test
    public void shouldGetReviewById() throws Exception {
        DogPark dogPark = DogPark.builder()
                .latitude(124.9)
                .longitude(45.8)
                .name("Ripleys park")
                .description("Agility")
                .build();
        Review review = Review.builder()
                .comment("topp")
                .rating(3)
                .dogpark(dogPark)
                .build();
        dogParkRepository.save(dogPark);
        reviewRepository.save(review);

        mockMvc.perform(get("/api/v1/review/id/" + dogPark.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].comment").value(review.getComment()))
                .andExpect(jsonPath("$[0].rating").value(review.getRating()))
                .andExpect(jsonPath("$[0].id").value(review.getId()));
    }

    @Test
    public void shouldCreateReview() throws Exception {
        DogPark givenDogPark = DogPark.builder()
                .latitude(124.9)
                .longitude(45.8)
                .name("Ripleys park")
                .description("Agility")
                .build();
        dogParkRepository.save(givenDogPark);

        ReviewRequestDto validReviewRequestDto = ReviewRequestDto.builder()
                .dogParkId(givenDogPark.getId())
                .rating(3)
                .comment("lalal")
                .build();

        mockMvc.perform(post("/api/v1/review/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validReviewRequestDto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/v1/review/id/" + givenDogPark.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].comment").value(validReviewRequestDto.getComment()))
                .andExpect(jsonPath("$[0].rating").value(validReviewRequestDto.getRating()));
    }

}

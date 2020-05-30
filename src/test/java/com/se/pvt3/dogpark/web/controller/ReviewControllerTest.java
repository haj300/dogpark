package com.se.pvt3.dogpark.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pvt3.dogpark.repository.DogPark;
import com.se.pvt3.dogpark.repository.Review;
import com.se.pvt3.dogpark.repository.ReviewRepository;
import com.se.pvt3.dogpark.services.DogParkService;
import com.se.pvt3.dogpark.services.ReviewService;
import com.se.pvt3.dogpark.web.dto.ReviewRequestDto;
import com.se.pvt3.dogpark.web.dto.ReviewResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest
@ExtendWith(MockitoExtension.class)
class ReviewControllerTest {

    @MockBean
    DogParkService dogParkService;

    @MockBean
    ReviewService reviewService;

    @MockBean
    ReviewRepository reviewRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    ReviewRequestDto validReviewRequestDto;
    ReviewResponseDto validReviewResponseDto;
    DogPark validDogPark;
    Review validReview;

    @BeforeEach
    void setUp() {
        validDogPark = DogPark.builder()
                .description("jsdhf")
                .latitude(127634.23)
                .longitude(1263713.7)
                .name("lakdkla")
                .id(132)
                .build();

        validReview = Review.builder()
                .comment("haha")
                .rating(2)
                .id(2)
                .dogpark(validDogPark)
                .build();

        validReviewResponseDto = ReviewResponseDto.builder()
                .id(2)
                .comment("Nämen!")
                .rating(2)
                .build();
    }


    @Test
    public void getAllReviewsTest() throws Exception {
        doReturn(List.of(validReview)).when(reviewService).getAllReviews();

        mockMvc.perform(get("/api/v1/review/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(validReviewResponseDto.getId()));

    }

    @Test
    void getReviewByDogParkId() throws Exception {
        doReturn(List.of(validReview)).when(reviewService).getReviewsByDogParkId(any(Integer.class));

        mockMvc.perform(get("/api/v1/review/id/" + validReviewResponseDto.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(validReviewResponseDto.getId()));
    }

    @Test
    void createReview() {
    }

    @Test
    void deleteReview() {
    }
}
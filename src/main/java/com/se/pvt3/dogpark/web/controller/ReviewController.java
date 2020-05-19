package com.se.pvt3.dogpark.web.controller;


import com.se.pvt3.dogpark.services.ReviewService;
import com.se.pvt3.dogpark.web.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/review")
@RestController
@RequiredArgsConstructor
public class ReviewController {


    private final ReviewService reviewService;


    @GetMapping("/all")
    public List<ReviewResponseDto> getAllReviews(){
        return reviewService.getAllReviews();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ReviewResponseDto> getReviewById(@PathVariable("id") int id){
        Optional<ReviewResponseDto> reviewById = reviewService.getReviewById(id);
        ReviewResponseDto reviewResponseDto = reviewById.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(reviewResponseDto, HttpStatus.OK);
    }


}

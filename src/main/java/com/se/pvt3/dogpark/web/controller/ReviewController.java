package com.se.pvt3.dogpark.web.controller;


import com.se.pvt3.dogpark.services.ReviewService;
import com.se.pvt3.dogpark.web.dto.ReviewRequestDto;
import com.se.pvt3.dogpark.web.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/review")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping(path = "/all")
    public ResponseEntity<ReviewResponseDto> getAllReviews(){
        List<ReviewResponseDto> allReviews = reviewService.getAllReviews();
        return new ResponseEntity(allReviews, HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<ReviewResponseDto> getReviewByDogParkId(@PathVariable("id")int id){
        List<ReviewResponseDto> reviewById = reviewService.getReviewsByDogParkId(id);
        return new ResponseEntity(reviewById, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createReview(@RequestBody ReviewRequestDto reviewRequestDto){
        reviewService.saveNewReview(reviewRequestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable int id){ reviewService.deleteById(id);}
}

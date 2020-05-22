package com.se.pvt3.dogpark.services;

import com.se.pvt3.dogpark.web.dto.ReviewRequestDto;
import com.se.pvt3.dogpark.web.dto.ReviewResponseDto;

import java.util.List;

public interface ReviewService {

    List<ReviewResponseDto> getReviewsByDogParkId(int id);

    List<ReviewResponseDto> getAllReviews();

    void saveNewReview(ReviewRequestDto reviewRequestDto);

    void deleteById(int id);
}

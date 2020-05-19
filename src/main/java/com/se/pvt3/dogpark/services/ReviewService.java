package com.se.pvt3.dogpark.services;

import com.se.pvt3.dogpark.web.dto.ReviewRequestDto;
import com.se.pvt3.dogpark.web.dto.ReviewResponseDto;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    Optional<ReviewResponseDto> getReviewById(int id);

    List<ReviewResponseDto> getAllReviews();

    void saveNewReview(ReviewRequestDto reviewRequestDto);

    void deleteById(int id);
}

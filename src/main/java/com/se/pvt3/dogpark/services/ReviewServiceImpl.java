package com.se.pvt3.dogpark.services;

import com.se.pvt3.dogpark.repository.DogPark;
import com.se.pvt3.dogpark.repository.DogParkRepository;
import com.se.pvt3.dogpark.repository.Review;
import com.se.pvt3.dogpark.repository.ReviewRepository;
import com.se.pvt3.dogpark.web.dto.ReviewRequestDto;
import com.se.pvt3.dogpark.web.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final DogParkRepository dogParkRepository;

    @Override
    public List<ReviewResponseDto> getReviewsByDogParkId(int id) {
        Optional<DogPark> optionalDogPark = dogParkRepository.findById(id);
        if (optionalDogPark.isPresent()) {
            DogPark dogPark = optionalDogPark.get();
            Set<Review> reviews = dogPark.getReviews();
            return reviews
                    .stream()
                    .map(review -> ReviewResponseDto.builder()
                            .comment(review.getComment())
                            .rating(review.getRating())
                            .build()).collect(Collectors.toList());
        } else {
            throw new DogParkNotFoundException();
        }
    }

    @Override
    public List<ReviewResponseDto> getAllReviews() {
        List<Review> all = reviewRepository.findAll();
        return all.stream()
                .map(review -> ReviewResponseDto.builder()
                        .rating(review.getRating())
                        .comment(review.getComment())
                        .id(review.getId())
                        .build()).collect(Collectors.toList());

    }

    @Override
    public void saveNewReview(ReviewRequestDto reviewRequestDto) {
        Optional<DogPark> optionalDogPark = dogParkRepository.findById(reviewRequestDto.getDogParkId());
        if (optionalDogPark.isPresent()) {
            DogPark dogPark = optionalDogPark.get();
            Set<Review> reviews = dogPark.getReviews();
            Review review = Review.builder()
                    .comment(reviewRequestDto.getComment())
                    .rating(reviewRequestDto.getRating())
                    .dogpark(dogPark)
                    .build();
            reviewRepository.save(review);
        } else {
            throw new DogParkNotFoundException();
        }
    }

    @Override
    public void deleteById(int id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        optionalReview.ifPresent(reviewRepository::delete);
        log.debug("Deleting review");
    }
}

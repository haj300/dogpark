package com.se.pvt3.dogpark.services;

import com.se.pvt3.dogpark.repository.DogPark;
import com.se.pvt3.dogpark.repository.DogParkRepository;
import com.se.pvt3.dogpark.repository.Review;
import com.se.pvt3.dogpark.repository.ReviewRepository;
import com.se.pvt3.dogpark.web.dto.ReviewRequestDto;
import com.se.pvt3.dogpark.web.dto.ReviewResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.se.pvt3.dogpark.helper.DogParkTestHelper.getDefaultDogPark;
import static com.se.pvt3.dogpark.helper.ReviewTestHelper.getDefaultReview;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {


    @Mock
    ReviewRepository reviewRepository;

    @Mock
    DogParkRepository dogParkRepository;

    ReviewService reviewService;

    @BeforeEach
    void setUp() {
        reviewService = new ReviewServiceImpl(reviewRepository, dogParkRepository);
    }

    @Test
    void getReviewsByDogParkId() {
        DogPark givenDogPark = getDefaultDogPark().build();
        Review givenReview = getDefaultReview()
                .dogpark(givenDogPark)
                .build();
        givenDogPark.setReviews(new HashSet<>(Set.of(givenReview)));
        doReturn(Optional.of(givenDogPark)).when(dogParkRepository).findById(givenDogPark.getId());

        List<ReviewResponseDto> reviewsByDogParkId = reviewService.getReviewsByDogParkId(givenDogPark.getId());
        ReviewResponseDto actualReviewResponseDto = reviewsByDogParkId.get(0);
        assertEquals(givenReview.getComment(), actualReviewResponseDto.getComment());
        assertEquals(givenReview.getRating(), actualReviewResponseDto.getRating());
        assertEquals(givenReview.getId(), actualReviewResponseDto.getId());
    }

    @Test
    void getAllReviews() {
        Review givenReview1 = getDefaultReview()
                .build();
        Review givenReview2 = getDefaultReview()
                .build();

        List<Review> givenListOfReviews = new ArrayList<>();
        givenListOfReviews.add(givenReview1);
        givenListOfReviews.add(givenReview2);
        doReturn(givenListOfReviews).when(reviewRepository).findAll();

        List<ReviewResponseDto> allReviews = reviewService.getAllReviews();
        ReviewResponseDto reviewResponseDto = allReviews.get(0);
        assertEquals(givenListOfReviews.size(), allReviews.size());
        assertEquals(givenReview1.getId(), reviewResponseDto.getId());
        assertEquals(givenReview1.getRating(), reviewResponseDto.getRating());
        assertEquals(givenReview1.getComment(), reviewResponseDto.getComment());
    }

    @Test
    void saveNewReview() {
        DogPark givenDogPark = getDefaultDogPark().build();
        ReviewRequestDto givenReviewRequestDto = getDefaultReviewRequestDto()
                .dogParkId(givenDogPark.getId())
                .build();
        ArgumentCaptor<Review> reviewArgumentCaptor = ArgumentCaptor.forClass(Review.class);
        doReturn(Optional.of(givenDogPark)).when(dogParkRepository).findById(givenReviewRequestDto.getDogParkId());
        
        reviewService.saveNewReview(givenReviewRequestDto);
        verify(reviewRepository, times(1)).save(reviewArgumentCaptor.capture());
        Review actualReview = reviewArgumentCaptor.getValue();
        assertEquals(givenReviewRequestDto.getComment(), actualReview.getComment());
    }


    @Test
    void deleteById() {
        DogPark givenDogPark = getDefaultDogPark().build();
        Review givenReview = getDefaultReview()
                .dogpark(givenDogPark)
                .build();
        givenDogPark.setReviews(new HashSet<>(Set.of(givenReview)));
        ArgumentCaptor<Review> reviewArgumentCaptor = ArgumentCaptor.forClass(Review.class);
        doReturn(Optional.of(givenReview)).when(reviewRepository).findById(givenReview.getId());

        reviewService.deleteById(givenReview.getId());
        verify(reviewRepository, times(1)).delete(reviewArgumentCaptor.capture());
        Review actualReview = reviewArgumentCaptor.getValue();
        assertEquals(givenReview, actualReview);

    }

    private ReviewRequestDto.ReviewRequestDtoBuilder getDefaultReviewRequestDto() {
        return ReviewRequestDto.builder()
                .comment("hshs")
                .rating(3)
                .dogParkId(8);
    }


}
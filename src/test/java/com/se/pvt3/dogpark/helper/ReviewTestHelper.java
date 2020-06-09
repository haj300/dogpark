package com.se.pvt3.dogpark.helper;

import com.se.pvt3.dogpark.repository.DogPark;
import com.se.pvt3.dogpark.repository.Review;

public class ReviewTestHelper {

    public static Review.ReviewBuilder getDefaultReview() {
        return Review.builder()
                .comment("hehej")
                .rating(2)
                .dogpark(DogPark.builder()
                        .description("hjshd")
                        .latitude(2763.6)
                        .longitude(73857.6)
                        .name("En park")
                        .id(23)
                        .build())
                .id(2);
    }
}

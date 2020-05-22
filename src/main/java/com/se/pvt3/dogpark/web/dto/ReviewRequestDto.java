package com.se.pvt3.dogpark.web.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Value
@Builder
public class ReviewRequestDto {

    int dogParkId;

    @Positive
    @Size(min = 0, max = 5)
    int rating;

    @Size(max = 500)
    String comment;
}

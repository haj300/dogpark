package com.se.pvt3.dogpark.web.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReviewResponseDto {

    int id;

    int rating;

    String comment;
}

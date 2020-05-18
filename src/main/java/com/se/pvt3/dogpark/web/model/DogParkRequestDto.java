package com.se.pvt3.dogpark.web.model;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Value
@Builder
public class DogParkRequestDto {

    @Positive
    double latitude;

    @Positive
    double longitude;

    @NotBlank
    String name;

    @Size(max = 500)
    String description;

}

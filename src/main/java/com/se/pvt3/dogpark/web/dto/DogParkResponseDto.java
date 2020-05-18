package com.se.pvt3.dogpark.web.dto;

import lombok.*;

@Value
@Builder
public class DogParkResponseDto {

    int id;

    double latitude;

    double longitude;

    String name;

    String description;

}

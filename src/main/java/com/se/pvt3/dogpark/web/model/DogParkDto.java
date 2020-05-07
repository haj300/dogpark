package com.se.pvt3.dogpark.web.model;

import lombok.*;

import java.util.UUID;

@Value
@Builder
public class DogParkDto {

    int id;
    double latitude;
    double longitude;
    String name;
    String description;

}

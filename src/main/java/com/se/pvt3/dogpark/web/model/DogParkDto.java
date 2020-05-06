package com.se.pvt3.dogpark.web.model;

import lombok.*;

import java.util.UUID;

@Value
@Builder
public class DogParkDto {

    UUID id;
    long latitude;
    long longitude;
    String name;
    String dogParkDescription;


}

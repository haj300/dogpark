package com.se.pvt3.dogpark.helper;

import com.se.pvt3.dogpark.repository.DogPark;

import java.util.Collections;

public class DogParkTestHelper {

    public static DogPark.DogParkBuilder getDefaultDogPark() {
        return DogPark.builder()
                .id(34)
                .name("En park")
                .longitude(123.45)
                .latitude(567.78)
                .description("Fin")
                .images(Collections.emptySet())
                .reviews(Collections.emptySet());
    }
}

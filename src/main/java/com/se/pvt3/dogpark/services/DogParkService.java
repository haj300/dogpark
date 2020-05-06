package com.se.pvt3.dogpark.services;

import com.se.pvt3.dogpark.web.model.DogParkDto;

import java.util.UUID;

public interface DogParkService {

    DogParkDto getDogParkById(UUID dogParkId);

    DogParkDto getDogParkByName(String name);
}

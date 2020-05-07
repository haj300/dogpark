package com.se.pvt3.dogpark.services;

import com.se.pvt3.dogpark.web.model.DogParkDto;

import java.util.UUID;

public interface DogParkService {

    DogParkDto getDogParkById(int dogParkId);

    DogParkDto getDogParkByName(String name);

    void saveNewDogPark(DogParkDto dogParkDto);

    void updateDogPark(int id, DogParkDto dogParkDto);

    void deleteById(int id);
}




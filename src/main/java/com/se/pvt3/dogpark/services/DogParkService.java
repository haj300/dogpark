package com.se.pvt3.dogpark.services;

import com.se.pvt3.dogpark.web.model.DogParkDto;

import java.util.Optional;

public interface DogParkService {

    Optional<DogParkDto> getDogParkById(int dogParkId);

    Optional<DogParkDto> getDogParkByName(String name);

    void saveNewDogPark(DogParkDto dogParkDto);

    void updateDogPark(int id, DogParkDto dogParkDto);

    void deleteById(int id);
}




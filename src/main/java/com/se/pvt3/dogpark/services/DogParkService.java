package com.se.pvt3.dogpark.services;

import com.se.pvt3.dogpark.web.model.DogParkRequestDto;
import com.se.pvt3.dogpark.web.model.DogParkResponseDto;

import java.util.Optional;

public interface DogParkService {

    Optional<DogParkResponseDto> getDogParkById(int dogParkId);

    Optional<DogParkResponseDto> getDogParkByName(String name);

    void saveNewDogPark(DogParkRequestDto dogParkRequestDto);

    void updateDogPark(int id, DogParkResponseDto dogParkResponseDto);

    void deleteById(int id);
}




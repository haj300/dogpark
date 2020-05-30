package com.se.pvt3.dogpark.services;

import com.se.pvt3.dogpark.web.dto.DogParkRequestDto;
import com.se.pvt3.dogpark.web.dto.DogParkResponseDto;

import java.util.List;
import java.util.Optional;

public interface DogParkService {

    Optional<DogParkResponseDto> getDogParkById(int dogParkId);

    Optional<DogParkResponseDto> getDogParkByName(String name);

    List<DogParkResponseDto> getAllDogs();

    void saveNewDogPark(DogParkRequestDto dogParkRequestDto);

    void updateDogPark(int id, DogParkRequestDto dogParkRequestDto);

    void deleteById(int id);

    Optional<List<DogParkResponseDto>> findByDistance(Double latitude, Double longitude, Double distance);
}




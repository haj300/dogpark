package com.se.pvt3.dogpark.services;

import com.se.pvt3.dogpark.repository.DogPark;
import com.se.pvt3.dogpark.repository.DogParkRepository;
import com.se.pvt3.dogpark.web.model.DogParkDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class DogParkServiceImpl implements DogParkService{

    @Autowired
    private final DogParkRepository dogParkRepository;

    public Optional<DogPark> findById(int id) {
        return dogParkRepository.findById(id);
    }

    @Override
    public DogParkDto getDogParkById(int dogParkId) {
        dogParkRepository.findById(dogParkId);

        return DogParkDto.builder()
                .name("lal")
                .description("descrip")
                .longitude(81928192)
                .latitude(5647)
                .build();
    }

    @Override
    public DogParkDto getDogParkByName(String name) {
        return DogParkDto.builder()
                .name("lal")
                .description("descrip")
                .longitude(81928192)
                .latitude(23672364)
                .build();
    }

    @Override
    public void saveNewDogPark(DogParkDto dogParkDto) {
        DogPark dogPark = DogPark.builder()
                .description(dogParkDto.getDescription())
                .name(dogParkDto.getName())
                .longitude(dogParkDto.getLongitude())
                .latitude(dogParkDto.getLatitude())
                .build();
        dogParkRepository.save(dogPark);
    }

    @Override
    public void updateDogPark(int id, DogParkDto dogParkDto) {

    }

    @Override
    public void deleteById(int id) {
        log.debug("Deleting dog park");
    }
}

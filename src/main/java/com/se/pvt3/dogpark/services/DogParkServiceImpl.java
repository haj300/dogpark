package com.se.pvt3.dogpark.services;

import com.se.pvt3.dogpark.repository.DogPark;
import com.se.pvt3.dogpark.repository.DogParkRepository;
import com.se.pvt3.dogpark.web.model.DogParkRequestDto;
import com.se.pvt3.dogpark.web.model.DogParkResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class DogParkServiceImpl implements DogParkService {

    @Autowired
    private final DogParkRepository dogParkRepository;

    @Override
    public Optional<DogParkResponseDto> getDogParkById(int dogParkId) {
        Optional<DogPark> optionalDogPark = dogParkRepository.findById(dogParkId);
        if (optionalDogPark.isPresent()) {
            DogPark dogPark = optionalDogPark.get();
            return Optional.of(DogParkResponseDto.builder()
                    .description(dogPark.getDescription())
                    .latitude(dogPark.getLatitude())
                    .longitude(dogPark.getLongitude())
                    .name(dogPark.getName())
                    .id(dogPark.getId())
                    .build());
        }
        return Optional.empty();
    }

    @Override
    public Optional<DogParkResponseDto> getDogParkByName(String dogParkName) {
        Optional<DogPark> optionalDogPark = dogParkRepository.findAllByName(dogParkName);
        if (optionalDogPark.isPresent()) {
            DogPark dogPark = optionalDogPark.get();
            return Optional.of(DogParkResponseDto.builder()
                    .description(dogPark.getDescription())
                    .latitude(dogPark.getLatitude())
                    .longitude(dogPark.getLongitude())
                    .name(dogPark.getName())
                    .id(dogPark.getId())
                    .build());
        }
        return Optional.empty();
    }

    @Override
    public void saveNewDogPark(DogParkRequestDto dogParkRequestDto) {
        DogPark dogPark = DogPark.builder()
                .description(dogParkRequestDto.getDescription())
                .name(dogParkRequestDto.getName())
                .longitude(dogParkRequestDto.getLongitude())
                .latitude(dogParkRequestDto.getLatitude())
                .build();
        dogParkRepository.save(dogPark);
    }

    @Override
    public void updateDogPark(int id, DogParkResponseDto dogParkResponseDto) {

    }

    @Override
    public void deleteById(int id) {
        log.debug("Deleting dog park");
    }
}


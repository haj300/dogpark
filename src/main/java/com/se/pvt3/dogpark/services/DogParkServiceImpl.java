package com.se.pvt3.dogpark.services;

import com.se.pvt3.dogpark.repository.DogPark;
import com.se.pvt3.dogpark.repository.DogParkRepository;
import com.se.pvt3.dogpark.web.dto.DogParkRequestDto;
import com.se.pvt3.dogpark.web.dto.DogParkResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class DogParkServiceImpl implements DogParkService {


    private final DogParkRepository dogParkRepository;

    @Override
    public Optional<DogParkResponseDto> getDogParkById(int dogParkId) {
        Optional<DogPark> optionalDogPark = dogParkRepository.findById(dogParkId);
        if (optionalDogPark.isPresent()) {
            DogPark dogPark = optionalDogPark.get();
            return Optional.of(toDto(dogPark));
        }
        return Optional.empty();
    }

    @Override
    public Optional<DogParkResponseDto> getDogParkByName(String dogParkName) {
        Optional<DogPark> optionalDogPark = dogParkRepository.findAllByName(dogParkName);
        if (optionalDogPark.isPresent()) {
            DogPark dogPark = optionalDogPark.get();
            return Optional.of(toDto(dogPark));
        }
        return Optional.empty();
    }

    @Override
    public List<DogParkResponseDto> getAllDogs() {
        List<DogPark> dogParks = dogParkRepository.findAll();
        return dogParks.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void saveNewDogPark(DogParkRequestDto dogParkRequestDto) {
        DogPark dogPark = toDogPark(dogParkRequestDto);
        dogParkRepository.save(dogPark);
    }

    @Override
    public void updateDogPark(int id, DogParkRequestDto dogParkRequestDto) {
        Optional<DogPark> optionalDogPark = dogParkRepository.findById(id);
        if(optionalDogPark.isPresent()){
            DogPark dogPark = optionalDogPark.get();
            dogPark.setDescription(dogParkRequestDto.getDescription());
            dogPark.setName(dogParkRequestDto.getName());
            dogPark.setLatitude(dogParkRequestDto.getLatitude());
            dogPark.setLongitude(dogParkRequestDto.getLongitude());
            dogParkRepository.save(dogPark);
        }
        else {
            throw new DogParkNotFoundException();
        };
    }

    @Override
    public void deleteById(int id) {
        Optional<DogPark> optionalDogPark = dogParkRepository.findById(id);
        optionalDogPark.ifPresent(dogParkRepository::delete);
        log.debug("Deleting dog park");
    }

    public Optional<List<DogParkResponseDto>> findByDistance(Double latitude, Double longitude, Double distance) {
        var result = dogParkRepository.findByPositionWithinDistance(latitude, longitude, distance);
        return buildOptional(result);
    }

    private Optional<List<DogParkResponseDto>> buildOptional(List<DogPark> list) {
        if (list.isEmpty()) {
            return Optional.empty();
        }
        var result = list.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return Optional.of(result);
    }

    private DogParkResponseDto toDto(DogPark dogPark) {
        return DogParkResponseDto.builder()
                .description(dogPark.getDescription())
                .latitude(dogPark.getLatitude())
                .longitude(dogPark.getLongitude())
                .name(dogPark.getName())
                .id(dogPark.getId())
                .build();
    }

    private DogPark toDogPark(DogParkRequestDto dogParkRequestDto) {
        return DogPark.builder()
                .description(dogParkRequestDto.getDescription())
                .name(dogParkRequestDto.getName())
                .longitude(dogParkRequestDto.getLongitude())
                .latitude(dogParkRequestDto.getLatitude())
                .build();
    }
}


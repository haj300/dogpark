package com.se.pvt3.dogpark.web.controller;

import com.se.pvt3.dogpark.services.DogParkService;
import com.se.pvt3.dogpark.web.dto.DogParkRequestDto;
import com.se.pvt3.dogpark.web.dto.DogParkResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api/v1/dog_park")
@RestController
@RequiredArgsConstructor
public class DogParkController {

    private final DogParkService dogParkService;

    @GetMapping("/dogparks")
    public List <DogParkResponseDto> getAllDogParks(){
        return dogParkService.getAllDogs();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<DogParkResponseDto> getDogParkByName(@NotNull @PathVariable("name") String name) {
        Optional<DogParkResponseDto> dogParkByName = dogParkService.getDogParkByName(name);
        DogParkResponseDto dogParkResponseDto = dogParkByName.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(dogParkResponseDto, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DogParkResponseDto> getDogParkById(@PathVariable("id") int id) {
        Optional<DogParkResponseDto> dogParkById = dogParkService.getDogParkById(id);
        DogParkResponseDto dogParkResponseDto = dogParkById.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(dogParkResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createDogPark(@Valid @RequestBody DogParkRequestDto dogParkRequestDto){
        dogParkService.saveNewDogPark(dogParkRequestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateDogPark(@PathVariable int id, @Valid @RequestBody DogParkRequestDto dogParkRequestDto){
        dogParkService.updateDogPark(id, dogParkRequestDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDogPark(@PathVariable int id){
        dogParkService.deleteById(id);
    }


}

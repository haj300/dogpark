package com.se.pvt3.dogpark.web.controller;

import com.se.pvt3.dogpark.services.DogParkService;
import com.se.pvt3.dogpark.web.model.DogParkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequestMapping("/api/v1/dog_park")
@RestController
@RequiredArgsConstructor
public class DogParkController {

    private final DogParkService dogParkService;

    @GetMapping("/name/{name}")
    public ResponseEntity<DogParkDto> getDogParkByName(@PathVariable("name") String name) {
        Optional<DogParkDto> dogParkByName = dogParkService.getDogParkByName(name);
        DogParkDto dogParkDto = dogParkByName.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(dogParkDto, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DogParkDto> getDogParkById(@PathVariable("id") int id) {
        Optional<DogParkDto> dogParkById = dogParkService.getDogParkById(id);
        DogParkDto dogParkDto = dogParkById.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(dogParkDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createDogPark(@RequestBody DogParkDto dogParkDto){
        dogParkService.saveNewDogPark(dogParkDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity handleUpdate(@PathVariable int id, @RequestBody DogParkDto dogParkDto){
        dogParkService.updateDogPark(id, dogParkDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDogPark(@PathVariable int id){
        dogParkService.deleteById(id);
    }

}

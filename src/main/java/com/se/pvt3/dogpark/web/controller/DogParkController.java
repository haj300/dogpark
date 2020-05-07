package com.se.pvt3.dogpark.web.controller;

import com.se.pvt3.dogpark.services.DogParkService;
import com.se.pvt3.dogpark.web.model.DogParkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/dog_park")
@RestController
@RequiredArgsConstructor
public class DogParkController {

    private final DogParkService dogParkService;

    @GetMapping("/name/{name}")
    public ResponseEntity<DogParkDto> getDogParkByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(dogParkService.getDogParkByName(name), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DogParkDto> getDogParkById(@PathVariable("id") int id) {
        return new ResponseEntity<>(dogParkService.getDogParkById(id), HttpStatus.OK);
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

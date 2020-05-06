package com.se.pvt3.dogpark.web.controller;

import com.se.pvt3.dogpark.services.DogParkService;
import com.se.pvt3.dogpark.web.model.DogParkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/dog_park")
@RestController
@RequiredArgsConstructor
public class DogParkController {

    private final DogParkService dogParkService;

    @GetMapping({"/name"})
    public ResponseEntity<DogParkDto> getDogParkByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(dogParkService.getDogParkByName(name), HttpStatus.OK);
    }

    @GetMapping({"/id"})
    public ResponseEntity<DogParkDto> getDogParkById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(dogParkService.getDogParkById(id), HttpStatus.OK);
    }

    /**
     * @GetMapping({"/"})
     *     public ResponseEntity<DogParkDto> get
     *             return new ResponseEntity<>(dogParkService.  ,HttpStatus.OK);
     */

}

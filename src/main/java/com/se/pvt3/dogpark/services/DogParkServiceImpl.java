package com.se.pvt3.dogpark.services;

import com.se.pvt3.dogpark.web.model.DogParkDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DogParkServiceImpl implements DogParkService{


    @Override
    public DogParkDto getDogParkById(UUID dogParkId) {
        return DogParkDto.builder().id(UUID.randomUUID())
                .name("lal")
                .dogParkDescription("descrip")
                .longitude(81928192)
                .latitude(23672364)
                .build();
    }

    @Override
    public DogParkDto getDogParkByName(String name) {
        return DogParkDto.builder().id(UUID.randomUUID())
                .name("lal")
                .dogParkDescription("descrip")
                .longitude(81928192)
                .latitude(23672364)
                .build();
    }

}

package com.se.pvt3.dogpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DogParkRepository extends JpaRepository<DogPark, Integer> {
    Optional<DogPark> findById(int id);

    Optional<DogPark> findAllByName(String name);
}


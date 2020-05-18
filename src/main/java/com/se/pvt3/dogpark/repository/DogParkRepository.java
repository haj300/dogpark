package com.se.pvt3.dogpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DogParkRepository extends JpaRepository<DogPark, Integer> {
    Optional<DogPark> findById(int id);

    Optional<DogPark> findAllByName(String name);

    List<DogPark> findAll();
}


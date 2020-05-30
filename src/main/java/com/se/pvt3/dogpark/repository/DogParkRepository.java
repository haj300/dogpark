package com.se.pvt3.dogpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DogParkRepository extends JpaRepository<DogPark, Integer> {
    Optional<DogPark> findById(int id);

    Optional<DogPark> findAllByName(String name);

    List<DogPark> findAll();

    @Query("FROM DogPark WHERE "
            + "(6371392.896 * acos(cos(radians(:latitude)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(latitude))))"
            + " < :distance ORDER BY "
            + "(6371392.896 * acos(cos(radians(:latitude)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(latitude))))"
            + " DESC")
    List<DogPark> findByPositionWithinDistance(Double latitude, Double longitude, Double distance);
}


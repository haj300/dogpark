package com.se.pvt3.dogpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {


    List<Review> findAll();

  //  List<Review> findByDogpark(DogPark dogpark, int id);
}

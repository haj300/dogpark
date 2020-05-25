package com.se.pvt3.dogpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository <Image, String> {

    List<Image> findAll();
}

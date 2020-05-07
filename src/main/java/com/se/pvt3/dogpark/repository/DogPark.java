package com.se.pvt3.dogpark.repository;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "dog_park")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DogPark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private int id;
    private Double latitude;
    private Double longitude;

    @Column(name="name", unique = true)
    private String name;
    private String description;

}

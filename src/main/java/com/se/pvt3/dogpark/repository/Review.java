package com.se.pvt3.dogpark.repository;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Review implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dogpark_id", nullable=false)
    DogPark dogpark;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    int id;

    private int rating;

    private String comment;

}

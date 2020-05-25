package com.se.pvt3.dogpark.repository;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dogpark_id", nullable=false)
    DogPark dogpark;

    @Column(name ="url")
    String url;

    public String toString() {
        return url;
    }
}

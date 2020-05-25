package com.se.pvt3.dogpark.services;

import java.net.URL;
import java.util.List;

public interface ImageServiceInterface {

    List<URL> getReviewsByDogParkId(int id);

}

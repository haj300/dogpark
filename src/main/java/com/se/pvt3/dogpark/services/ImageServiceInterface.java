package com.se.pvt3.dogpark.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageServiceInterface {

    String getPicturesByParkId(int id);

    void uploadPictureToParkById(int id, MultipartFile multipartFile, boolean enablePublicReadAccess);

}

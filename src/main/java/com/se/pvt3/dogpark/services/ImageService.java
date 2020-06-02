package com.se.pvt3.dogpark.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.se.pvt3.dogpark.repository.DogPark;
import com.se.pvt3.dogpark.repository.DogParkRepository;
import com.se.pvt3.dogpark.repository.Image;
import com.se.pvt3.dogpark.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ImageService implements ImageServiceInterface {

    @Autowired
    private final String awsS3AudioBucket;
    @Autowired
    private final AmazonS3 amazonS3;

    @Autowired
    private final ImageRepository imageRepository;
    @Autowired
    private final DogParkRepository dogParkRepository;

    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    public void uploadPictureToParkById(int id, MultipartFile multipartFile, boolean enablePublicReadAccess) {

        //Get dogPark that should use the image
        Optional<DogPark> dogPark = dogParkRepository.findById(id);

        if (dogPark.isPresent()) {

            //Create a new image to store the URL of the file that is being created
            Image image = new Image();

            //Creating the file
            String fileName = multipartFile.getOriginalFilename();

            try {
                //creating the file in the server (temporarily)
                File file = new File(fileName);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(multipartFile.getBytes());
                fos.close();

                for (int i = -1; i < dogPark.get().getImages().size(); i++) {

                    if (i == dogPark.get().getImages().size() - 1) {
                        fileName = dogPark.get().getName() + "/" + dogPark.get().getName() + (i + 1) + "." + FilenameUtils.getExtension(fileName);
                    }
                }

                //Set the URL to the new image and connect with the correct dog park and save it to the repository
                image.setUrl("https://dogparks.s3.amazonaws.com/" + fileName);
                image.setDogpark(dogPark.get());
                imageRepository.save(image);

                //Add the image to the dog park
                dogPark.get().getImages().add(image);

                PutObjectRequest putObjectReqeust = new PutObjectRequest(this.awsS3AudioBucket, fileName, file);

                if (enablePublicReadAccess) {
                    putObjectReqeust.withCannedAcl(CannedAccessControlList.PublicRead);
                }

                this.amazonS3.putObject(putObjectReqeust);

            } catch (IOException | AmazonServiceException ex) {
                logger.error("error [" + ex.getMessage() + "] occurred while uploading [" + fileName + "] ");
            }
        }
        else {
            throw new DogParkNotFoundException();
        }
    }

        public String getPicturesByParkId(int id) {

            Optional<DogPark> dogPark = dogParkRepository.findById(id);

            if (dogPark.isPresent()) {
                Set<Image> images = dogPark.get().getImages();
                System.out.println(images.toString());
                return images.toString(); }
            else {
                throw new DogParkNotFoundException();
            }
        }

}

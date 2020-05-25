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
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class ImageService {

    private String awsS3AudioBucket;
    private AmazonS3 amazonS3;

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private DogParkRepository dogParkRepository;

    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    @Autowired
    public ImageService(Region awsRegion, AWSCredentialsProvider awsCredentialsProvider, String awsS3AudioBucket) {
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion.getName()).build();
        this.awsS3AudioBucket = awsS3AudioBucket;
    }

    public void uploadPictureToParkById(int id, MultipartFile multipartFile, boolean enablePublicReadAccess) {

        //Get dogPark that should use the image
        Optional<DogPark> dogPark = dogParkRepository.findById(id);

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

            for(int i = -1; i < dogPark.get().getImages().size(); i++) {

                if(i == dogPark.get().getImages().size() - 1) {
                    fileName = "picture " + dogPark.get().getName() + (i + 1) + "." + FilenameUtils.getExtension(fileName);
                }
            }

            //Set the URL to the new image and connect with the correct dog park and save it to the repository
            image.setUrl("https://dog-profile-pictures.s3.amazonaws.com/" + fileName);
            image.setDogpark(dogPark.get());
            imageRepository.save(image);

            //Add the image to the dog park
            dogPark.get().getImages().add(image);

            PutObjectRequest putObjectReqeust = new PutObjectRequest(this.awsS3AudioBucket, fileName, file);

            if (enablePublicReadAccess) {
                putObjectReqeust.withCannedAcl(CannedAccessControlList.PublicRead);
            }

            this.amazonS3.putObject(putObjectReqeust);

            //removing the file created in the server
            file.delete();

        } catch (IOException | AmazonServiceException ex) {
            logger.error("error [" + ex.getMessage() + "] occurred while uploading [" + fileName + "] ");
        }
    }

        public URL getPicturesByParkId(int id) throws MalformedURLException {

        Optional<DogPark> dogPark = dogParkRepository.findById(id);
        //List<String> parkImages = dogPark.get().getImages();
        //Implementera så att alla URL skickas tillbaka!
        return new URL("tjenatjena.se");
    }
}
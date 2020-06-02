package com.se.pvt3.dogpark.services;

import com.amazonaws.services.s3.AmazonS3;
import com.se.pvt3.dogpark.repository.DogPark;
import com.se.pvt3.dogpark.repository.DogParkRepository;
import com.se.pvt3.dogpark.repository.Image;
import com.se.pvt3.dogpark.repository.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.se.pvt3.dogpark.helper.DogParkTestHelper.getDefaultDogPark;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {

    @Mock
    DogParkRepository dogParkRepository;

    @Mock
    ImageRepository imageRepository;

    @Mock
    AmazonS3 amazonS3;

    ImageService imageService;

    @BeforeEach
    void setUp() {
        imageService = new ImageService("testBucket", amazonS3, imageRepository, dogParkRepository);
    }

    @Test
    void uploadPictureToParkByIdWithoutEnablePublicReadAccess() {
        DogPark givenDogPark = getDefaultDogPark().build();
        Image givenImage = new Image(3, givenDogPark, "url");
        givenDogPark.setImages(new HashSet<>(Set.of(givenImage)));
        doReturn(Optional.of(givenDogPark)).when(dogParkRepository).findById(givenDogPark.getId());
        MultipartFile givenMultipartFile = new MockMultipartFile("testFile", "originalFileName", null, "testBytes".getBytes());
        doReturn(null).when(imageRepository).save(any());

        imageService.uploadPictureToParkById(givenDogPark.getId(), givenMultipartFile, false);
        verify(amazonS3).putObject(any());
    }

    @Test
    void uploadPictureToParkByIdWithEnablePublicReadAccess() {
        DogPark givenDogPark = getDefaultDogPark().build();
        Image givenImage = new Image(3, givenDogPark, "url");
        givenDogPark.setImages(new HashSet<>(Set.of(givenImage)));
        doReturn(Optional.of(givenDogPark)).when(dogParkRepository).findById(givenDogPark.getId());
        MultipartFile givenMultipartFile = new MockMultipartFile("testFile", "originalFileName", null, "testBytes".getBytes());
        doReturn(null).when(imageRepository).save(any());

        imageService.uploadPictureToParkById(givenDogPark.getId(), givenMultipartFile, true);
        verify(amazonS3).putObject(any());
    }

    @Test
    void getPicturesByParkId() {
        DogPark givenDogPark = getDefaultDogPark().build();
        Image givenImage = new Image(3, givenDogPark, "url");
        givenDogPark.setImages(new HashSet<>(Set.of(givenImage)));
        doReturn(Optional.of(givenDogPark)).when(dogParkRepository).findById(givenDogPark.getId());
        String picturesByParkId = imageService.getPicturesByParkId(givenDogPark.getId());

        assertEquals(Set.of(givenImage.getUrl()).toString(), picturesByParkId);
    }
}
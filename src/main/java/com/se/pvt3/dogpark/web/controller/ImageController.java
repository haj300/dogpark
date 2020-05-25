package com.se.pvt3.dogpark.web.controller;

import com.se.pvt3.dogpark.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    /**
     * CALL: localhost:8080/image/addImage?id=XXXXX
     * I bodyn skickas det med en RequestPart som är en fil med namnet file
     */
    @PutMapping(path = "addImage", params = "id")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam("id") int id) {
        imageService.uploadPictureToParkById(id, file, true);
        return "File [" + file.getOriginalFilename() + "] uploaded to storage";
    }

    /** CALL: localhost:8080/image/getImages?id=XXXXX*/

    @GetMapping(path = "getImages", params = "id")
    @ResponseBody
    public URL getPictureFromId(@RequestParam(value = "id") int id) throws MalformedURLException {
        return imageService.getPicturesByParkId(id);
    }
}
package com.se.pvt3.dogpark.client;

import com.se.pvt3.dogpark.web.model.DogParkDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(prefix = "pvt.dogpark", ignoreUnknownFields = false)
public class DogParkClient {

    public final String DOGPARK_PATH_V1 = "/api/v1/dogpark/";
    private String apihost;

    private final RestTemplate restTemplate;


    public DogParkClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public DogParkDto getDogParkById(int id){
        return restTemplate.getForObject(apihost + DOGPARK_PATH_V1 + id, DogParkDto.class);
    }

    public void setApihost(String apihost){
        this.apihost = apihost;
    }
}

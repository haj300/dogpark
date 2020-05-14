package com.se.pvt3.dogpark.client;

import com.se.pvt3.dogpark.web.model.DogParkDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DogParkClientTest {

    @Autowired
    DogParkClient client;

    @Test
    void getDogParkById() {
        DogParkDto dto = client.getDogParkById(1);

        assertNotNull(dto);
    }
}
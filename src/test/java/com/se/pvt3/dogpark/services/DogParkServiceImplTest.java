package com.se.pvt3.dogpark.services;

import com.se.pvt3.dogpark.repository.DogPark;
import com.se.pvt3.dogpark.repository.DogParkRepository;
import com.se.pvt3.dogpark.web.dto.DogParkRequestDto;
import com.se.pvt3.dogpark.web.dto.DogParkResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogParkServiceImplTest {

    @Mock
    DogParkRepository dogParkRepository;

    DogParkServiceImpl dogParkService;

    @BeforeEach
    void setUp() {
        dogParkService = new DogParkServiceImpl(dogParkRepository);
    }

    @Test
    void getDogParkById() {
        DogPark givenDogPark = getDefaultDogPark().build();
        Optional<DogPark> givenOptionalDogPark = Optional.of(givenDogPark);
        doReturn(givenOptionalDogPark).when(dogParkRepository).findById(givenDogPark.getId());

        Optional<DogParkResponseDto> optionalDogParkResponseDto = dogParkService.getDogParkById(givenDogPark.getId());
        DogParkResponseDto actualDogParkResponseDto = optionalDogParkResponseDto.get();
        assertEquals(givenDogPark.getId(), actualDogParkResponseDto.getId());
        assertEquals(givenDogPark.getName(), actualDogParkResponseDto.getName());
        assertEquals(givenDogPark.getDescription(), actualDogParkResponseDto.getDescription());
        assertEquals(givenDogPark.getLongitude(), actualDogParkResponseDto.getLongitude());
        assertEquals(givenDogPark.getLatitude(), actualDogParkResponseDto.getLatitude());
    }

    @Test
    void getDogParkByName() {
        DogPark givenDogPark = getDefaultDogPark().build();
        Optional<DogPark> givenOptionalDogPark = Optional.of(givenDogPark);
        doReturn(givenOptionalDogPark).when(dogParkRepository).findAllByName(givenDogPark.getName());

        Optional<DogParkResponseDto> optionalDogParkResponseDto = dogParkService.getDogParkByName(givenDogPark.getName());
        DogParkResponseDto actualDogParkResponseDto = optionalDogParkResponseDto.get();
        assertEquals(givenDogPark.getName(), actualDogParkResponseDto.getName());
        assertEquals(givenDogPark.getDescription(), actualDogParkResponseDto.getDescription());
        assertEquals(givenDogPark.getLongitude(), actualDogParkResponseDto.getLongitude());
        assertEquals(givenDogPark.getLatitude(), actualDogParkResponseDto.getLatitude());
    }

    @Test
    void getAllDogs() {
        DogPark givenDogPark = getDefaultDogPark().build();
        DogPark givenDogPark2 = getDefaultDogPark().id(67).build();
        List<DogPark> givenDogParkList = List.of(givenDogPark, givenDogPark2);
        doReturn(givenDogParkList).when(dogParkRepository).findAll();

        List<DogParkResponseDto> allDogs = dogParkService.getAllDogs();
        assertEquals(givenDogParkList.size(), allDogs.size());
    }

    @Test
    void getAllDogsLongList() {
        List<DogPark> givenDogParkList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            givenDogParkList.add(getDefaultDogPark().id(i).build());
        }
        doReturn(givenDogParkList).when(dogParkRepository).findAll();

        List<DogParkResponseDto> allDogs = dogParkService.getAllDogs();
        assertEquals(givenDogParkList.size(), allDogs.size());
        assertFalse(allDogs.isEmpty());
    }

    @Test
    void saveNewDogPark() {
        DogParkRequestDto givenDogParkRequestDto = getDefaultRequestDto();
        ArgumentCaptor<DogPark> dogParkArgumentCaptor = ArgumentCaptor.forClass(DogPark.class);

        dogParkService.saveNewDogPark(givenDogParkRequestDto);
        verify(dogParkRepository, times(1)).save(dogParkArgumentCaptor.capture());
        DogPark actualDogPark = dogParkArgumentCaptor.getValue();
        assertEquals(givenDogParkRequestDto.getName(), actualDogPark.getName());
        //TODO
    }

    @Test
    void updateDogPark() {
        DogPark givenDogPark = getDefaultDogPark().build();
        doReturn(Optional.of(givenDogPark)).when(dogParkRepository).findById(givenDogPark.getId());
        DogParkRequestDto givenDogParkRequestDto = getDefaultRequestDto();
        ArgumentCaptor<DogPark> dogParkArgumentCaptor = ArgumentCaptor.forClass(DogPark.class);

        dogParkService.updateDogPark(givenDogPark.getId(), givenDogParkRequestDto);

        verify(dogParkRepository, times(1)).save(dogParkArgumentCaptor.capture());
        DogPark actualDogPark = dogParkArgumentCaptor.getValue();
        assertEquals(givenDogParkRequestDto.getName(), actualDogPark.getName());
        //TODO
    }

    @Test
    void deleteById() {
    }

    @Test
    void findByDistance() {
    }

    private DogPark.DogParkBuilder getDefaultDogPark() {
        return DogPark.builder()
                .id(34)
                .name("En park")
                .longitude(123.45)
                .latitude(567.78)
                .description("Fin")
                .images(Collections.emptySet())
                .reviews(Collections.emptySet());
    }

    private DogParkRequestDto getDefaultRequestDto() {
        return DogParkRequestDto.builder()
                .description("Så bra")
                .latitude(465.78)
                .longitude(672.89)
                .name("Vitan")
                .build();
    }
}
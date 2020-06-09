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

import static com.se.pvt3.dogpark.helper.DogParkTestHelper.getDefaultDogPark;
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
    void shouldReturnExceptionWhenDogParkIdNotFoundInDatabase() {
        doReturn(Optional.empty()).when(dogParkRepository).findById(10);
        assertThrows(DogParkNotFoundException.class, () -> dogParkService.getDogParkById(10));
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
    void shouldReturnExceptionWhenDogParkNameNotFoundInDatabase() {
        doReturn(Optional.empty()).when(dogParkRepository).findAllByName("Ej här");
        assertThrows(DogParkNotFoundException.class, () -> dogParkService.getDogParkByName("Ej här"));
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
        assertEquals(givenDogParkRequestDto.getDescription(), givenDogParkRequestDto.getDescription());
        assertEquals(givenDogParkRequestDto.getLongitude(), givenDogParkRequestDto.getLongitude());
        assertEquals(givenDogParkRequestDto.getLatitude(), givenDogParkRequestDto.getLatitude());
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
        assertEquals(givenDogParkRequestDto.getDescription(), givenDogParkRequestDto.getDescription());
        assertEquals(givenDogParkRequestDto.getLongitude(), givenDogParkRequestDto.getLongitude());
        assertEquals(givenDogParkRequestDto.getLatitude(), givenDogParkRequestDto.getLatitude());
    }

    @Test
    void deleteById() {
        DogPark givenDogPark = getDefaultDogPark().build();
        doReturn(Optional.of(givenDogPark)).when(dogParkRepository).findById(givenDogPark.getId());
        ArgumentCaptor<DogPark> dogParkArgumentCaptor = ArgumentCaptor.forClass(DogPark.class);

        dogParkService.deleteById(givenDogPark.getId());
        verify(dogParkRepository, times(1)).delete(dogParkArgumentCaptor.capture());
        DogPark actualDogPark = dogParkArgumentCaptor.getValue();
        assertEquals(givenDogPark, actualDogPark);
    }

    @Test
    void findByDistance() {
        DogPark givenDogPark = getDefaultDogPark().build();
        List<DogPark> givenDogParkList = List.of(givenDogPark);
        doReturn(givenDogParkList).when(dogParkRepository)
                .findByPositionWithinDistance(givenDogPark.getLatitude(), givenDogPark.getLongitude(), 57.78);

        Optional<List<DogParkResponseDto>> dogParkResponseDtoList = dogParkService
                .findByDistance(givenDogPark.getLatitude(), givenDogPark.getLongitude(), 57.78);
        List<DogParkResponseDto> dogParkResponseDtos = dogParkResponseDtoList.get();
        DogParkResponseDto dogParkResponseDto = dogParkResponseDtos.get(0);
        assertEquals(givenDogPark.getLatitude(), dogParkResponseDto.getLatitude());
        assertEquals(givenDogPark.getLongitude(), dogParkResponseDto.getLongitude());
        assertEquals(givenDogPark.getName(), dogParkResponseDto.getName());
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
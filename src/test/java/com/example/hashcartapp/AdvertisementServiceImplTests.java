package com.example.hashcartapp;

import com.example.hashcartapp.constants.Location;
import com.example.hashcartapp.constants.Type;
import com.example.hashcartapp.dto.AdvertisementDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.repository.AdvertisementRepository;
import com.example.hashcartapp.service.impl.AdvertisementServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;

import static com.example.hashcartapp.constants.Category.VEHICLES;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdvertisementServiceImplTests {

    @Mock
    AdvertisementRepository advertisementRepository;

    @InjectMocks
    AdvertisementServiceImpl advertisementServiceImpl;

    @Mock
    ModelMapper modelMapper;

    @Test
    public void given_get_all_advertisements_should_return_list_of_all_advertisements(){
        advertisementServiceImpl.getAllAdvertisements();
        verify(advertisementRepository,times(1)).findAll();
    }

    @Test
    public void given_advertisement_id_then_should_return_advertisement_of_that_id(){
        advertisementServiceImpl.getAdvertisementById(12L);
        verify(advertisementRepository,times(1)).findById(12L);
    }

    @Test
    public void given_advertisement_to_add_should_return_added_advertisement(){
        AdvertisementDTO advertisement = new AdvertisementDTO(12L, "Selling of car", "SELL", "VEHICLES", "Delhi", "", 300000L, 400000L, "",new Date(),  new Date(),true, true, new ArrayList<>(), new ArrayList<>());
        when(modelMapper.map(Mockito.any(), eq(Advertisement.class))).thenReturn(new Advertisement());
        when(modelMapper.map(Mockito.any(), eq(AdvertisementDTO.class))).thenReturn(new AdvertisementDTO());
        advertisementServiceImpl.saveAdvertisement(advertisement);
        verify(advertisementRepository,times(1)).save(Mockito.any());
    }



    @Test
    public void given_advertisement_category_then_should_return_advertisement_of_that_category(){
        advertisementServiceImpl.findAdvertisementByCategory(VEHICLES, Pageable.unpaged());
        verify(advertisementRepository,times(1)).
                findAdvertisementByCategoryOrderByDateDesc("VEHICLES", Pageable.unpaged());
    }

    @Test
    public void given_advertisement_type_then_should_return_advertisement_of_that_type(){
        advertisementServiceImpl.findAdvertisementByType(Type.SELL, Pageable.unpaged());
        verify(advertisementRepository,times(1)).
                findAdvertisementByTypeOrderByDateDesc("SELL", Pageable.unpaged());
    }

    @Test
    public void given_advertisement_location_then_should_return_advertisement_of_that_location(){
        advertisementServiceImpl.findAdvertisementByLocation(Location.Delhi, Pageable.unpaged());
        verify(advertisementRepository,times(1)).
                findAdvertisementByLocationOrderByDateDesc("Delhi", Pageable.unpaged());
    }


    @Test
    public void given_advertisement_id_then_should_delete_advertisement_of_that_id(){
        advertisementServiceImpl.deleteAdvertisement(49L);
        verify(advertisementRepository,times(1)).deleteById(49L);
    }


}

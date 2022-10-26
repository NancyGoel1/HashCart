package com.example.hashcartapp;

import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.repository.AdvertisementRepository;
import com.example.hashcartapp.service.AdvertisementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AdvertisementServiceTests {

    @Mock
    AdvertisementRepository advertisementRepository;

    @InjectMocks
    AdvertisementService advertisementService;

    @Test
    public void given_get_all_advertisements_should_return_list_of_all_advertisements(){
        advertisementService.getAllAdvertisements();
        verify(advertisementRepository,times(1)).findAll();
    }

    @Test
    public void given_advertisement_id_then_should_return_advertisement_of_that_id(){
        advertisementService.getAdvertisementById(12L);
        verify(advertisementRepository,times(1)).findById(12L);
    }

    @Test
    public void given_advertisement_to_add_should_return_added_advertisement(){
        Advertisement advertisement = new Advertisement(12L, "Selling of car", "Sell", "Vehicle", "", "Delhi", 300000L, 400000L, "","2022-10-11 00:00:00",
                "2022-10-11 00:00:00");
        advertisementService.saveAdvertisement(advertisement);
        verify(advertisementRepository,times(1)).save(advertisement);
    }

    @Test
    public void given_advertisement_category_then_should_return_advertisement_of_that_category(){
        advertisementService.findAdvertisementByCategory("Vehicle", Pageable.unpaged());
        verify(advertisementRepository,times(1)).
                findAdvertisementByCategoryOrderByDateDesc("Vehicle", Pageable.unpaged());
    }

    @Test
    public void given_advertisement_type_then_should_return_advertisement_of_that_type(){
        advertisementService.findAdvertisementByCategory("Sell", Pageable.unpaged());
        verify(advertisementRepository,times(1)).
                findAdvertisementByCategoryOrderByDateDesc("Sell", Pageable.unpaged());
    }

    @Test
    public void given_advertisement_location_then_should_return_advertisement_of_that_location(){
        advertisementService.findAdvertisementByCategory("Delhi", Pageable.unpaged());
        verify(advertisementRepository,times(1)).
                findAdvertisementByCategoryOrderByDateDesc("Delhi", Pageable.unpaged());
    }

    @Test
    public void given_advertisement_id_then_should_delete_advertisement_of_that_id(){
        advertisementService.deleteAdvertisement(49L);
        verify(advertisementRepository,times(1)).deleteById(49L);
    }


}

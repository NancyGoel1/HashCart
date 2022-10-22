package com.example.hashcartapp;

import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.repository.AdvertisementRepository;
import com.example.hashcartapp.service.AdvertisementService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvertisementControllerTest {

    @MockBean
    AdvertisementService advertisementService;

    @MockBean
    AdvertisementRepository advertisementRepository;

/*    @Test
    public void getAllAdvertisementTest(){
         when(advertisementRepository.findAll()).thenReturn(Stream.
                of(new Advertisement(12L, "Selling of car", "Sell", "Vehicle", "", "Delhi", 300000L, 400000L, "",ZonedDateTime.now(ZoneId.of("UTC")),
                        ZonedDateTime.now(ZoneId.of("UTC"))),
                        new Advertisement(13L, "Buying of analog watch", "Buy", "Electronic", "","Mumbai", 3000L, 4000L,""ZonedDateTime.now(ZoneId.of("UTC")),
                                ZonedDateTime.now(ZoneId.of("UTC"))),
                        new Advertisement( 14L, "Selling of analog watch", "Sell", "Electronic", "", "Meerut", 35000L, 45000L,"",ZonedDateTime.now(ZoneId.of("UTC")),
                                ZonedDateTime.now(ZoneId.of("UTC")))).collect(Collectors.toList()));
        assertEquals(3,advertisementService.getAllAdvertisements());
    }*/

    @Test
    public void getAdvertisementTest(){
        Long advertisementId= Long.valueOf(12);
        Advertisement advertisement = new Advertisement(12L, "Selling of car", "Sell", "Vehicle", "", "Delhi", 300000L, 400000L, "","2022-10-11 00:00:00",
                "2022-10-11 00:00:00");

        when(advertisementRepository.findById(advertisementId)).thenReturn(Optional.of(advertisement));
        assertEquals(1,advertisementService.getAdvertisementById(advertisementId));
    }

    @Test
    public void saveAdvertisementTest(){
        Advertisement advertisement = new Advertisement(15L, "Buying a 2 BHK Flat", "Buy", "House", "", "Delhi", 5000000L, 7000000L, "9876123401","2022-10-11 00:00:00",
                "2022-10-11 00:00:00");

        when(advertisementRepository.save(advertisement)).thenReturn(advertisement);
        assertEquals(advertisement,advertisementService.saveAdvertisement(advertisement));
    }

    @Test
    public void deleteAdvertisementTest(){
        Advertisement advertisement = new Advertisement(15L, "Buying a 2 BHK Flat", "Buy", "House", "", "Delhi", 5000000L, 7000000L, "9876123401","2022-10-11 00:00:00",
                "2022-10-11 00:00:00");

        advertisementRepository.delete(advertisement);
        verify(advertisementRepository,times(1)).delete(advertisement);
    }



}

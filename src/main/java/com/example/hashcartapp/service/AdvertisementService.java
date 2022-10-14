package com.example.hashcartapp.service;

import com.example.hashcartapp.dto.AdvertisementDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hibernate.internal.CoreLogging.logger;

@Service
public class AdvertisementService {

    @Autowired
    AdvertisementRepository advertisementRepository;

    public List<AdvertisementDTO> getAllAdvertisements(){
        return advertisementRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public AdvertisementDTO getAdvertisementById(Long advertisementId) {
        AdvertisementDTO advertisementToReturn = null;

        try {
            Optional<Advertisement> advertisementFound = advertisementRepository.findById(advertisementId);

            if (advertisementFound.isPresent()) {
                advertisementToReturn = advertisementFound.map(this::convertEntityToDTO).get();
            }

        } catch (NoSuchElementException e) {
            logger("No advertisement found in the table having this id");
            e.printStackTrace();
        } catch (IllegalArgumentException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return advertisementToReturn;
    }


    public Advertisement saveAdvertisement(Advertisement advertisement){
        return advertisementRepository.save(advertisement);
    }

    public AdvertisementDTO  convertEntityToDTO(Advertisement advertisement){
        AdvertisementDTO advertisementDTO = new AdvertisementDTO();
        advertisementDTO.setAdvertisementId(advertisement.getAdvertisementId());
        advertisementDTO.setDescription(advertisement.getDescription());
        advertisementDTO.setType(advertisement.getType());
        advertisementDTO.setCategory(advertisement.getCategory());
        advertisementDTO.setPriceRangeLower(advertisement.getPriceRangeLower());
        advertisementDTO.setPriceRangeHigher(advertisement.getPriceRangeHigher());
        advertisementDTO.setCreationDate(advertisement.getCreationDate());
        advertisementDTO.setClosedDate(advertisement.getClosedDate());
        return advertisementDTO;
    }
}

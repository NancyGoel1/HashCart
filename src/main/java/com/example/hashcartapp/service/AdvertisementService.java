package com.example.hashcartapp.service;

import com.example.hashcartapp.controller.AdvertisementController;
import com.example.hashcartapp.dto.AdvertisementDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.repository.AdvertisementRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertisementService {

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(AdvertisementController.class);

    public List<AdvertisementDTO> getAllAdvertisements(){
        return advertisementRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public boolean isAdvertisementActive(Long advertisementId){
        ZonedDateTime activeDate = advertisementRepository.findById(advertisementId).get().getClosedDate();
        ZonedDateTime todayDate= ZonedDateTime.now();
        if(todayDate.isAfter(activeDate))
            advertisementRepository.findById(advertisementId).get().setActive(false);
        return advertisementRepository.findById(advertisementId).get().isActive();
    }


   /* public boolean isAdvertisementDeleted(Long advertisementId){
       boolean adDeleted = advertisementRepository.findById(advertisementId).get().isAdvertisementDeleted();
       if(adDeleted == false)
         advertisementRepository.findById(advertisementId).get().setAdvertisementDeleted(true);
        boolean b = advertisementRepository.findById(advertisementId).get().isAdvertisementDeleted();
        advertisementRepository.deleteAd(advertisementId);
        logger.info("{}",advertisementService.isAdvertisementDeleted(advertisementId));

        return true;
    }*/

    public AdvertisementDTO getAdvertisementById(Long advertisementId) {
        AdvertisementDTO advertisementToReturn = null;

        try {
            Optional<Advertisement> advertisementFound = advertisementRepository.findById(advertisementId);

            if (advertisementFound.isPresent()) {
                advertisementToReturn = advertisementFound.map(this::convertEntityToDTO).get();
            }

        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
        } catch (IllegalArgumentException e1) {
            logger.error(e1.getMessage());
        } catch (Exception e2) {
            logger.error(e2.getMessage());

        }
        return advertisementToReturn;
    }


    public Advertisement saveAdvertisement(Advertisement advertisement){
        return advertisementRepository.save(advertisement);
    }


    public void deleteAdvertisement(Long advertisementId){
        advertisementRepository.deleteById(advertisementId);
    }

    public Page<Advertisement> findAdvertisementByCategory(String category, Pageable pageable) {
         return advertisementRepository.findAdvertisementByCategoryOrderByDateDesc(category,pageable);
    }

    public Page<Advertisement> findAdvertisementByType(String type,Pageable pageable) {
        return advertisementRepository.findAdvertisementByTypeOrderByDateDesc(type,pageable);
    }

    public Page<Advertisement> findAdvertisementByLocation(String location,Pageable pageable) {
        return advertisementRepository.findAdvertisementByLocationOrderByDateDesc(location,pageable);
    }


    public AdvertisementDTO  convertEntityToDTO(Advertisement advertisement){
        AdvertisementDTO advertisementDTO = this.modelMapper.map(advertisement,AdvertisementDTO.class);
        return advertisementDTO;
    }

    public Advertisement convertDTOToEntity(AdvertisementDTO advertisementDTO)
    {
        Advertisement advertisement=this.modelMapper.map(advertisementDTO,Advertisement.class);
        return advertisement;
    }

    public AdvertisementDTO updateAdvertisement(AdvertisementDTO advertisementDTO, Long advertisementId) {

        AdvertisementDTO adInTable = getAdvertisementById(advertisementId);
        if(adInTable != null) {
            adInTable.setAdvertisementId(advertisementDTO.getAdvertisementId());
            adInTable.setDescription(advertisementDTO.getDescription());
            adInTable.setType(advertisementDTO.getType());
            adInTable.setCategory(advertisementDTO.getCategory());
            adInTable.setLocation(advertisementDTO.getLocation());
            adInTable.setContactNo(advertisementDTO.getContactNo());
            adInTable.setCreationDate(advertisementDTO.getCreationDate());
            adInTable.setClosedDate(advertisementDTO.getClosedDate());
            adInTable.setPriceRangeLower(advertisementDTO.getPriceRangeLower());
            adInTable.setPriceRangeHigher(advertisementDTO.getPriceRangeHigher());
            adInTable.setLikes(advertisementDTO.getLikes());
            adInTable.setComments(advertisementDTO.getComments());

            AdvertisementDTO advertisementFound = advertisementService.getAdvertisementById(adInTable.getAdvertisementId());

            //    Advertisement advertisementToSave = adInTable.map(this::convertDTOToEntity).get();
            Advertisement advertisementEntity = this.modelMapper.map(adInTable, Advertisement.class);

            advertisementRepository.save(advertisementEntity);

            adInTable = this.modelMapper.map(advertisementEntity, AdvertisementDTO.class);
        }
        return adInTable;
    }

}

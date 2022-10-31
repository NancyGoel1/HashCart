package com.example.hashcartapp.service.impl;

import com.example.hashcartapp.constants.Category;
import com.example.hashcartapp.constants.Location;
import com.example.hashcartapp.constants.Type;
import com.example.hashcartapp.controller.AdvertisementController;
import com.example.hashcartapp.dto.AdvertisementDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.repository.AdvertisementRepository;
import com.example.hashcartapp.repository.UserRepository;
import com.example.hashcartapp.service.AdvertisementService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    AdvertisementRepository advertisementRepository;


    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(AdvertisementController.class);

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
            logger.error(e.getMessage());
        }
        return advertisementToReturn;
    }


    public AdvertisementDTO saveAdvertisement(AdvertisementDTO advertisementDTO){

        Advertisement advertisement = this.modelMapper.map(advertisementDTO, Advertisement.class);
        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);
        return this.modelMapper.map(savedAdvertisement, AdvertisementDTO.class);
    }


    public void deleteAdvertisement(Long advertisementId){
        advertisementRepository.deleteById(advertisementId);
    }

    public Page<Advertisement> findAdvertisementByCategory(Category category, Pageable pageable) {
         return advertisementRepository.findAdvertisementByCategoryOrderByDateDesc(category.toString(),pageable);
    }

    public Page<Advertisement> findAdvertisementByType(Type type, Pageable pageable) {
        return advertisementRepository.findAdvertisementByTypeOrderByDateDesc(type.toString(),pageable);
    }

    public Page<Advertisement> findAdvertisementByLocation(Location location, Pageable pageable) {
        return advertisementRepository.findAdvertisementByLocationOrderByDateDesc(location.toString(),pageable);
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
            adInTable.setActive(advertisementDTO.isActive());
            adInTable.setAdvertisementDeleted(advertisementDTO.isAdvertisementDeleted());
            adInTable.setLikes(advertisementDTO.getLikes());
            adInTable.setComments(advertisementDTO.getComments());

            AdvertisementDTO advertisementFound = advertisementService.getAdvertisementById(adInTable.getAdvertisementId());

            Advertisement advertisementEntity = this.modelMapper.map(adInTable, Advertisement.class);

            advertisementRepository.save(advertisementEntity);

            adInTable = this.modelMapper.map(advertisementEntity, AdvertisementDTO.class);
        }
        return adInTable;
    }

}

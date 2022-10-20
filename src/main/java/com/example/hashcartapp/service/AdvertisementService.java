package com.example.hashcartapp.service;

import com.example.hashcartapp.dto.AdvertisementDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.entities.User;
import com.example.hashcartapp.repository.AdvertisementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hibernate.internal.CoreLogging.logger;

@Service
public class AdvertisementService {

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    ModelMapper modelMapper;

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


    public void deleteAdvertisement(Long advertisementId){
        advertisementRepository.deleteById(advertisementId);
    }

    public Page<Advertisement> findAdvertisementByCategory(String category, Pageable pageable) {
        return advertisementRepository.findAdvertisementByCategoryOrderByDateDesc(category,pageable);
    }

    public Page<Advertisement> findAdvertisementByType(Pageable pageable) {
        return advertisementRepository.findAdvertisementByTypeOrderByDateDesc(pageable);
    }

    public Page<Advertisement> findAdvertisementByLocation(Pageable pageable) {
        return advertisementRepository.findAdvertisementByLocationOrderByDateDesc(pageable);
    }


    public AdvertisementDTO  convertEntityToDTO(Advertisement advertisement){
        AdvertisementDTO advertisementDTO = new AdvertisementDTO();
        //AdvertisementDTO advertisementDTO = this.modelMapper.map(advertisement,AdvertisementDTO.class);
        advertisementDTO.setAdvertisementId(advertisement.getAdvertisementId());
        advertisementDTO.setDescription(advertisement.getDescription());
        advertisementDTO.setType(advertisement.getType());
        advertisementDTO.setCategory(advertisement.getCategory());
        advertisementDTO.setLocation(advertisement.getLocation());
        advertisementDTO.setImage(advertisement.getImage());
        advertisementDTO.setPriceRangeLower(advertisement.getPriceRangeLower());
        advertisementDTO.setPriceRangeHigher(advertisement.getPriceRangeHigher());
        advertisementDTO.setContactNo(advertisement.getContactNo());
        advertisementDTO.setCreationDate(advertisement.getCreationDate());
        advertisementDTO.setClosedDate(advertisement.getClosedDate());
        advertisementDTO.setLikes(advertisement.getLikes());
        advertisementDTO.setComments(advertisement.getComments());
        return advertisementDTO;
    }

    public Advertisement convertDTOToEntity(AdvertisementDTO advertisementDTO)
    {
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertisementId(advertisementDTO.getAdvertisementId());
        advertisement.setCategory(advertisementDTO.getCategory());
        advertisement.setDescription(advertisementDTO.getDescription());
        advertisement.setType(advertisementDTO.getType());
        advertisement.setImage(advertisementDTO.getImage());
        advertisement.setLocation(advertisementDTO.getLocation());
        advertisement.setContactNo(advertisementDTO.getContactNo());
        advertisement.setCreationDate(advertisementDTO.getCreationDate());
        advertisement.setClosedDate(advertisementDTO.getClosedDate());
        advertisement.setPriceRangeLower(advertisementDTO.getPriceRangeLower());
        advertisement.setPriceRangeHigher(advertisementDTO.getPriceRangeHigher());
        advertisement.setLikes(advertisementDTO.getLikes());
        advertisement.setComments(advertisementDTO.getComments());
        return advertisement;
    }

    /*public AdvertisementDTO updateAdvertisement(AdvertisementDTO advertisementDTO, Long advertisementId) {

        // get book to be updated
        AdvertisementDTO adInTable = getAdvertisementById(advertisementId);
        if(adInTable != null) {

            //update the attributes
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
//  Advertisement advertisementToSave = adInTable.map(this::convertDTOToEntity).get();
            //Author author = authorService.getAuthorById(bookInTable.getAuthorId());

            Advertisement advertisementEntity = this.modelMapper.map(adInTable,Advertisement.class);
            // convert dto to entity
          //  Book bookEntity = mapper.DTOToEntity(bookInTable, author);

            advertisementRepository.save(advertisementEntity);
            // save the entity
            //bookRepository.save(bookEntity);
            adInTable = this.modelMapper.map(advertisementEntity,AdvertisementDTO.class);
            // convert entity to dto
          //  bookInTable = mapper.entityToDTO(bookEntity);

            // convert entity to dt
        }

        return adInTable;
    }*/

}

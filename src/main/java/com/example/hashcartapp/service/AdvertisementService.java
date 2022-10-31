package com.example.hashcartapp.service;

import com.example.hashcartapp.constants.Category;
import com.example.hashcartapp.constants.Location;
import com.example.hashcartapp.constants.Type;
import com.example.hashcartapp.dto.AdvertisementDTO;
import com.example.hashcartapp.entities.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdvertisementService {
    public List<AdvertisementDTO> getAllAdvertisements();

    public AdvertisementDTO getAdvertisementById(Long advertisementId);


    public AdvertisementDTO saveAdvertisement(AdvertisementDTO advertisementDTO);


    public void deleteAdvertisement(Long advertisementId);

    public Page<Advertisement> findAdvertisementByCategory(Category category, Pageable pageable);

    public Page<Advertisement> findAdvertisementByType(Type type, Pageable pageable);

    public Page<Advertisement> findAdvertisementByLocation(Location location, Pageable pageable);


    public AdvertisementDTO  convertEntityToDTO(Advertisement advertisement);

    public Advertisement convertDTOToEntity(AdvertisementDTO advertisementDTO);

    public AdvertisementDTO updateAdvertisement(AdvertisementDTO advertisementDTO, Long advertisementId);
}

package com.example.hashcartapp.service;

import com.example.hashcartapp.dto.LikesDTO;
import com.example.hashcartapp.entities.Likes;
import com.example.hashcartapp.repository.AdvertisementRepository;
import com.example.hashcartapp.repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesService {

       @Autowired
       LikesRepository likesRepository;

       @Autowired
       AdvertisementRepository advertisementRepository;



       public LikesDTO convertEntityToDTO(Likes likes){
              LikesDTO likesDTO = new LikesDTO();
              likesDTO.setLikesId(likes.getLikesId());
              likesDTO.setLikesCreatedAt(likes.getLikesCreatedAt());
              return likesDTO;
       }
}

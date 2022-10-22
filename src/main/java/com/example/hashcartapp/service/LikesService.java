package com.example.hashcartapp.service;

import com.example.hashcartapp.dto.LikesDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.entities.Likes;
import com.example.hashcartapp.repository.AdvertisementRepository;
import com.example.hashcartapp.repository.LikesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LikesService {

       @Autowired
       LikesRepository likesRepository;

       @Autowired
       AdvertisementRepository advertisementRepository;

       @Autowired
       ModelMapper modelMapper;

       public LikesDTO createLike(LikesDTO likesDTO,Long advertisementId){
              Advertisement advertisement = this.advertisementRepository.
                      findById(advertisementId).
                      orElseThrow(()->new NoSuchElementException("Advertisement not found"));

              Likes likes = this.modelMapper.map(likesDTO,Likes.class);
              likes.setAdvertisement(advertisement);
              Likes savedLikes = this.likesRepository.save(likes);
              return this.modelMapper.map(savedLikes,LikesDTO.class);
       }

       public void dislike(Long likesId){
              Likes likes = this.likesRepository.findById(likesId).
                      orElseThrow(()-> new NoSuchElementException("Likes not found"));
              this.likesRepository.delete(likes);
       }


       /*public LikesDTO convertEntityToDTO(Likes likes){
              LikesDTO likesDTO = new LikesDTO();
              likesDTO.setLikesId(likes.getLikesId());
              likesDTO.setLikesCreatedAt(likes.getLikesCreatedAt());
              return likesDTO;
       }*/
}

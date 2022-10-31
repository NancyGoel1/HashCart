package com.example.hashcartapp.service.impl;

import com.example.hashcartapp.dto.LikesDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.entities.Likes;
import com.example.hashcartapp.entities.User;
import com.example.hashcartapp.repository.AdvertisementRepository;
import com.example.hashcartapp.repository.LikesRepository;
import com.example.hashcartapp.repository.UserRepository;
import com.example.hashcartapp.service.LikesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LikesServiceImpl implements LikesService {

       @Autowired
       LikesRepository likesRepository;

       @Autowired
       AdvertisementRepository advertisementRepository;

       @Autowired
       UserRepository userRepository;

       @Autowired
       ModelMapper modelMapper;

       public LikesDTO createLike(LikesDTO likesDTO,Long advertisementId, Long userId){
              Advertisement advertisement = this.advertisementRepository.
                      findById(advertisementId).
                      orElseThrow(()->new NoSuchElementException("Advertisement not found"));
              User user = this.userRepository.
                      findById(userId).
                      orElseThrow(()->new NoSuchElementException("User not found"));
              Likes likes = this.modelMapper.map(likesDTO,Likes.class);
              likes.setAdvertisement(advertisement);
              likes.setUser(user);
              Likes savedLikes = this.likesRepository.save(likes);
              return this.modelMapper.map(savedLikes,LikesDTO.class);
       }

       public void dislike(Long likesId){
              this.likesRepository.deleteById(likesId);
       }

}

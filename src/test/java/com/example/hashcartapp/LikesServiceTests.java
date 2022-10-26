package com.example.hashcartapp;

import com.example.hashcartapp.dto.LikesDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.entities.Likes;
import com.example.hashcartapp.repository.AdvertisementRepository;
import com.example.hashcartapp.repository.LikesRepository;
import com.example.hashcartapp.service.LikesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LikesServiceTests {

    @Mock
    LikesRepository likesRepository;

    @Mock
    AdvertisementRepository advertisementRepository;

    @InjectMocks
    LikesService likesService;

    @Mock
    ModelMapper modelMapper;

    @Test
    public void given_like_to_add_should_return_added_like_on_that_advertisement(){
        when(advertisementRepository.findById(Mockito.any())).thenReturn(Optional.of(new Advertisement()));
        when(modelMapper.map(Mockito.any(), eq(Likes.class))).thenReturn(new Likes());
        when(modelMapper.map(Mockito.any(), eq(LikesDTO.class))).thenReturn(new LikesDTO());
        LikesDTO likes = new LikesDTO(25L, ZonedDateTime.now());
        likesService.createLike(likes, 12L);
        verify(likesRepository,times(1)).save(Mockito.any());

    }

    @Test
    public void given_likes_id_then_should_delete_likes_of_that_id(){
        likesService.dislike(37L);
        verify(likesRepository,times(1)).deleteById(37L);
    }
}

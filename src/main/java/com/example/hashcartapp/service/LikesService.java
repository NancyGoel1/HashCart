package com.example.hashcartapp.service;

import com.example.hashcartapp.dto.LikesDTO;

public interface LikesService {

    public LikesDTO createLike(LikesDTO likesDTO, Long advertisementId, Long userId);

    public void dislike(Long likesId);
}

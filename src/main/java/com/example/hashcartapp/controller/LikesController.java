package com.example.hashcartapp.controller;

import com.example.hashcartapp.dto.LikesDTO;
import com.example.hashcartapp.response.ApiResponse;
import com.example.hashcartapp.service.LikesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LikesController {

    @Autowired
    LikesService likesService;

    Logger logger = LoggerFactory.getLogger(LikesController.class);


    /**
     * User can like the advertisement
     * @param likesDTO
     * @param advertisementId
     * @param userId
     * @return User like the particular advertisement
     */
    @PostMapping("/user/{userId}/advertisement/{advertisementId}/likes")
    public ResponseEntity<LikesDTO> createLike(@Valid @RequestBody LikesDTO likesDTO, @PathVariable Long advertisementId, @PathVariable Long userId){
        LikesDTO likes = this.likesService.createLike(likesDTO, advertisementId, userId);
        logger.info("Advertisement is liked successfully");
        return new ResponseEntity<LikesDTO>(likes, HttpStatus.CREATED);
    }

    /**
     * User can dislike the advertisement
     * @param likesId
     * @return User dislike the particular advertisement
     */
    @DeleteMapping("likes/{likesId}")
    public ResponseEntity<ApiResponse> disLike(@Valid @PathVariable Long likesId){
        this.likesService.dislike(likesId);
        logger.info("Advertisement is disliked successfully");
        return new ResponseEntity<ApiResponse>(new ApiResponse("Advertisement is disliked successfully",true),HttpStatus.OK);
    }
}

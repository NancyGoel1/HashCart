package com.example.hashcartapp.controller;

import com.example.hashcartapp.dto.LikesDTO;
import com.example.hashcartapp.response.ApiResponse;
import com.example.hashcartapp.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class LikesController {

    @Autowired
    LikesService likesService;

    /**
     * User can like the advertisement
     * @param likesDTO
     * @param advertisementId
     * @return User can like the particular advertisement
     */

    @PostMapping("/advertisement/{advertisementId}/likes")
    public ResponseEntity<LikesDTO> createLike(@Valid @RequestBody LikesDTO likesDTO, @PathVariable Long advertisementId){
        LikesDTO likes = this.likesService.createLike(likesDTO, advertisementId);
        return new ResponseEntity<LikesDTO>(likes, HttpStatus.CREATED);
    }

    /**
     * User can dislike the advertisement
     * @param likesId
     * @return User can dislike the particular advertisement
     */
    @DeleteMapping("likes/{likesId}")
    public ResponseEntity<ApiResponse> disLike(@Valid @PathVariable Long likesId){
        this.likesService.dislike(likesId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Advertisement is disliked successfully",true),HttpStatus.OK);

    }

}

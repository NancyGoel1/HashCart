package com.example.hashcartapp.controller;

import com.example.hashcartapp.dto.CommentDTO;
import com.example.hashcartapp.entities.Comment;
import com.example.hashcartapp.response.ApiResponse;
import com.example.hashcartapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;
     @PostMapping("/advertisement/{advertisementId}/comments")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO, @PathVariable Long advertisementId){

         CommentDTO comment = this.commentService.createComment(commentDTO,advertisementId);

         return new ResponseEntity<CommentDTO>(comment, HttpStatus.CREATED);
     }

     @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@Valid @PathVariable Long commentId){

         this.commentService.deleteComment(commentId);

         return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
     }
}

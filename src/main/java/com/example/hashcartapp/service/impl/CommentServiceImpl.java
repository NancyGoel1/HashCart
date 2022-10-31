package com.example.hashcartapp.service.impl;

import com.example.hashcartapp.controller.AdvertisementController;
import com.example.hashcartapp.dto.CommentDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.entities.Comment;
import com.example.hashcartapp.entities.User;
import com.example.hashcartapp.repository.AdvertisementRepository;
import com.example.hashcartapp.repository.CommentRepository;
import com.example.hashcartapp.repository.UserRepository;
import com.example.hashcartapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

      @Autowired
      CommentRepository commentRepository;
      @Autowired
      AdvertisementRepository advertisementRepository;

      @Autowired
      UserRepository userRepository;

      @Autowired
      ModelMapper modelMapper;

      Logger logger = LoggerFactory.getLogger(AdvertisementController.class);


    public List<CommentDTO> getAllComments(){
        return commentRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

       public CommentDTO getCommentById(Long commentId){


           CommentDTO commentToReturn = null;

           try {
               Optional<Comment> commentFound = commentRepository.findById(commentId);
               if (commentFound.isPresent()) {
                   commentToReturn = commentFound.map(this::convertEntityToDTO).get();
               }

           } catch (NoSuchElementException e) {
               logger.error(e.getMessage());
           } catch (IllegalArgumentException e1) {
               logger.error(e1.getMessage());
           } catch (Exception e2) {
               logger.error(e2.getMessage());
           }
           return commentToReturn;

      }

      public CommentDTO createComment(CommentDTO commentDTO, Long advertisementId,Long userId){

          Advertisement advertisement = this.advertisementRepository.
                  findById(advertisementId).
                  orElseThrow(()->new NoSuchElementException("Advertisement not found"));
          User user = this.userRepository.
                  findById(userId).
                  orElseThrow(()->new NoSuchElementException("User not found"));
         Comment comment = this.modelMapper.map(commentDTO, Comment.class);

         comment.setAdvertisement(advertisement);
         comment.setUser(user);
         Comment savedComment = this.commentRepository.save(comment);
         return this.modelMapper.map(savedComment, CommentDTO.class);
      }

      public void deleteComment(Long commentId){
          this.commentRepository.deleteById(commentId);
      }
     public CommentDTO convertEntityToDTO(Comment comment)
      {
          CommentDTO commentDTO = this.modelMapper.map(comment, CommentDTO.class);
          return commentDTO;
      }

}
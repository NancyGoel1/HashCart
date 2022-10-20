package com.example.hashcartapp.service;

import com.example.hashcartapp.dto.CommentDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.entities.Comment;
import com.example.hashcartapp.repository.AdvertisementRepository;
import com.example.hashcartapp.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CommentService {

      @Autowired
      CommentRepository commentRepository;

      @Autowired
      AdvertisementRepository advertisementRepository;

      @Autowired
      ModelMapper modelMapper;

      public CommentDTO createComment(CommentDTO commentDTO, Long advertisementId){

          Advertisement advertisement = this.advertisementRepository.
                  findById(advertisementId).
                  orElseThrow(()->new NoSuchElementException("Advertisement not found"));

         Comment comment = this.modelMapper.map(commentDTO, Comment.class);

         comment.setAdvertisement(advertisement);

         Comment savedComment = this.commentRepository.save(comment);

         return this.modelMapper.map(savedComment, CommentDTO.class);
      }

      public void deleteComment(Long commentId){
          Comment comment = this.commentRepository.findById(commentId).
                  orElseThrow(()-> new NoSuchElementException("Comment not found"));
          this.commentRepository.delete(comment);
      }
    /*  public CommentDTO convertEntityToDTO(Comment comment)
      {
          CommentDTO commentDTO = new CommentDTO();
            UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
            CommentDTO commentDTO = this.modelMapper
          return commentDTO;
      }*/
}

package com.example.hashcartapp.service;

import com.example.hashcartapp.dto.CommentDTO;
import com.example.hashcartapp.entities.Comment;

import java.util.List;

public interface CommentService {

    public List<CommentDTO> getAllComments();

    public CommentDTO getCommentById(Long commentId);

    public CommentDTO createComment(CommentDTO commentDTO, Long advertisementId, Long userId);

    public void deleteComment(Long commentId);
    public CommentDTO convertEntityToDTO(Comment comment);

}

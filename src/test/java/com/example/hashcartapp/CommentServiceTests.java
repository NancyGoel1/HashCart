package com.example.hashcartapp;

import com.example.hashcartapp.dto.CommentDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.entities.Comment;
import com.example.hashcartapp.repository.AdvertisementRepository;
import com.example.hashcartapp.repository.CommentRepository;
import com.example.hashcartapp.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTests {

    @Mock
    CommentRepository commentRepository;

    @Mock
    AdvertisementRepository advertisementRepository;

    @InjectMocks
    CommentService commentService;

    @Mock
    ModelMapper modelMapper;


    @Test
    public void given_get_all_comments_should_return_list_of_all_comments(){
        commentService.getAllComments();
        verify(commentRepository,times(1)).findAll();
    }

    @Test
    public void given_id_then_should_return_comment_of_that_id(){
        commentService.getCommentById(33L);
        verify(commentRepository,times(1)).findById(33L);
    }

    @Test
    public void given_comment_to_add_should_return_comment_on_that_advertisement(){
        when(advertisementRepository.findById(Mockito.any())).thenReturn(Optional.of(new Advertisement()));
        when(modelMapper.map(Mockito.any(), eq(Comment.class))).thenReturn(new Comment());
        when(modelMapper.map(Mockito.any(), eq(CommentDTO.class))).thenReturn(new CommentDTO());
        CommentDTO comment = new CommentDTO(1L, "Nice post", ZonedDateTime.now());
        commentService.createComment(comment,12L);
        verify(commentRepository,times(1)).save(Mockito.any());

    }

    @Test
    public void given_comment_id_then_should_delete_comment_of_that_id(){
        commentService.deleteComment(48L);
        verify(commentRepository,times(1)).deleteById(48L);
    }

}

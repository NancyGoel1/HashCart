package com.example.hashcartapp;

import com.example.hashcartapp.repository.CommentRepository;
import com.example.hashcartapp.service.CommentService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTests {

    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CommentService commentService;

    @Mock
    ModelMapper modelMapper;


   /* @Test
    public void given_comment_on_advertisement_id_to_add_comment_on_that_advertisement_id(){
        CommentDTO commentDTO = new CommentDTO(32L, "This car is too expensive");
        Comment comment= this.modelMapper.map(commentDTO, Comment.class);
        commentService.createComment(commentDTO,12L);
        verify(commentRepository,times(1)).save(comment);
    }*/

}

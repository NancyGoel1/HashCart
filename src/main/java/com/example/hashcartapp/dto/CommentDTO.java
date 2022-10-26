package com.example.hashcartapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

@Data
public class CommentDTO {

    private Long commentId;

    @NotBlank(message = "Enter the comment")
    private String commentText;

    private ZonedDateTime commentCreationAt;

    /*public CommentDTO(Long commentId, String commentText, String commentCreationAt) {
    }*/

    public CommentDTO(Long commentId, String commentText, ZonedDateTime commentCreationAt) {
        this.commentId = commentId;
        this.commentText = commentText;
        this.commentCreationAt = commentCreationAt;
    }

    public CommentDTO(){

    }
}

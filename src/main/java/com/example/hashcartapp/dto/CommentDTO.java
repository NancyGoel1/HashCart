package com.example.hashcartapp.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CommentDTO {

    private Long commentId;

    private String commentText;

    private ZonedDateTime commentCreationAt;
}

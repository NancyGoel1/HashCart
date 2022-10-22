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

   /* private CommentDTO(long l, String this_car_is_too_expensive) {
    }*/
}

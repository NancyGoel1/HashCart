package com.example.hashcartapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long commentId;

    @NotBlank(message = "Enter the comment")
    private String commentText;

    private ZonedDateTime commentCreationAt = ZonedDateTime.now(ZoneId.of("UTC"));

}

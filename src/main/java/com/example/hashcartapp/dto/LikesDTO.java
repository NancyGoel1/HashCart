package com.example.hashcartapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

@Data
public class LikeDTO {

      private Long LikeId;

      private ZonedDateTime LikeCreatedAt;
}

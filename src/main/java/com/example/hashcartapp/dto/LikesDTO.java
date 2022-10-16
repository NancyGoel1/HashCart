package com.example.hashcartapp.dto;

import lombok.Data;

import java.time.Instant;
import java.time.ZonedDateTime;

@Data
public class LikesDTO {

      private Long likesId;

      private Instant likesCreatedAt;
}

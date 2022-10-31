package com.example.hashcartapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikesDTO {

      private Long likesId;

      private ZonedDateTime likesCreatedAt =ZonedDateTime.now(ZoneId.of("UTC"));
}

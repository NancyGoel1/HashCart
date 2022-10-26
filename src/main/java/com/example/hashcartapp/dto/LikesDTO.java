package com.example.hashcartapp.dto;

import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class LikesDTO {

      private Long likesId;

      private ZonedDateTime likesCreatedAt;

      public LikesDTO() {
      }

      public LikesDTO(Long likesId, ZonedDateTime likesCreatedAt) {
            this.likesId = likesId;
            this.likesCreatedAt = likesCreatedAt;
      }
/*  public LikesDTO(Long likesId, String likesCreatedAt) {
      }*/
}

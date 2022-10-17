package com.example.hashcartapp.dto;

import com.example.hashcartapp.entities.Comment;
import com.example.hashcartapp.entities.Likes;
import lombok.Data;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class AdvertisementDTO {

    public Long advertisementId;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Category is required")
    private String category;

    private Blob image;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = " Lower price is required")
    private Long priceRangeLower;

    @NotBlank(message = "Higher price is required")
    private Long priceRangeHigher;

    @NotNull
    @Digits(integer = 10,fraction = 0 , message = "enter correct phone")
    private String contactNo;

    @NotBlank(message = "Creation date is required")
    private ZonedDateTime creationDate;

    @NotBlank(message = "Closed date is required")
    private ZonedDateTime closedDate;

    private List<Comment> comments;

    private List<Likes> likes;

}

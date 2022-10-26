package com.example.hashcartapp.dto;

import com.example.hashcartapp.entities.Comment;
import com.example.hashcartapp.entities.Likes;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private String image;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = " Lower price is required")
    private Long priceRangeLower;

    @NotNull(message = "Higher price is required")
    private Long priceRangeHigher;

    @NotBlank
    @Digits(integer = 10,fraction = 0 , message = "enter correct phone")
    private String contactNo;

    @NotNull(message = "Creation date is required")
    private ZonedDateTime creationDate;

    @NotNull(message = "Closed date is required")
    private ZonedDateTime closedDate;

    private boolean isAdvertisementDeleted = false;

    private boolean isActive = true;

    private List<Comment> comments;

    private List<Likes> likes;

}

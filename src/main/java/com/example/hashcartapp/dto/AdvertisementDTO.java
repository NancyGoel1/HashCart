package com.example.hashcartapp.dto;

import com.example.hashcartapp.entities.Comment;
import com.example.hashcartapp.entities.Likes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementDTO {

    public Long advertisementId;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Category is required")
    @Enumerated(EnumType.STRING)
    @Column(name="category")
    private String category;

    @NotBlank(message = "Type is required")
    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private String type;

    @NotBlank(message = "Location is required")
    @Enumerated(EnumType.STRING)
    @Column(name="location")
   private String location;

    private String image;

    @NotNull(message = " Lower price is required")
    private Long priceRangeLower;

    @NotNull(message = "Higher price is required")
    private Long priceRangeHigher;

    @NotBlank
    @Digits(integer = 10,fraction = 0 , message = "enter correct phone")
    private String contactNo;

    @NotNull(message = "Creation date is required")
    private Date creationDate;

    @NotNull(message = "Closed date is required")
    private Date closedDate;

    private boolean isAdvertisementDeleted = false;

    private boolean isActive = true;

    private List<Comment> comments;

    private List<Likes> likes;

}

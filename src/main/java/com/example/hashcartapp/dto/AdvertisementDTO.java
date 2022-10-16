package com.example.hashcartapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class AdvertisementDTO {

    public Long advertisementId;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = " Lower price is required")
    private Long priceRangeLower;

    @NotBlank(message = "Higher price is required")
    private Long priceRangeHigher;

    @NotBlank(message = "Creation date is required")
    private ZonedDateTime creationDate;

    @NotBlank(message = "Closed date is required")
    private ZonedDateTime closedDate;

}

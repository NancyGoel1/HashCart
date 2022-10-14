package com.example.hashcartapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long advertisementId;

    @Column(length=500)
    private String description;

    private String type;

    private String category;

    private String location;

    private Long priceRangeLower;

    private Long priceRangeHigher;

    private ZonedDateTime creationDate;

    private ZonedDateTime closedDate;

    @ManyToOne
    private User user;

}


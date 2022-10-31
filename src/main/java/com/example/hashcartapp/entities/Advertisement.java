package com.example.hashcartapp.entities;

import com.example.hashcartapp.constants.Category;
import com.example.hashcartapp.constants.Location;
import com.example.hashcartapp.constants.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(name="category")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private Type type;

    @Enumerated(EnumType.STRING)
    @Column(name="location")
    private Location location;

    @Lob
    @Column(length=Integer.MAX_VALUE)
    private String image;


    private Long priceRangeLower;

    private Long priceRangeHigher;

    private String contactNo;

    private Date creationDate;

    private Date closedDate;

    private boolean isActive = true;

    private boolean isAdvertisementDeleted = false;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user") @JsonIgnore
    private List<Likes> likes;

    public Advertisement(Long advertisementId, String description, Type type, Category category, String image, Location location, Long priceRangeLower,Long priceRangeHigher, String contactNo, String creationDate, String closedDate) {
    }



}


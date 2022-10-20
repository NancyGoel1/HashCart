package com.example.hashcartapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Blob;
import java.time.ZonedDateTime;
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

    private String type;

    private String category;

    @Lob
    @Column(length=Integer.MAX_VALUE)
    private String image;

    private String location;

    private Long priceRangeLower;

    private Long priceRangeHigher;

    private String contactNo;

    private ZonedDateTime creationDate;

    private ZonedDateTime closedDate;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private List<Likes> likes;

    public Advertisement(Long advertisementId, String description, String type, String category, String image, String location, Long priceRangeLower,Long priceRangeHigher, String contactNo) {
    }

}


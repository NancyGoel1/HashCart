package com.example.hashcartapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likesId;

    @LastModifiedDate
    private Instant likesCreatedAt;

    @OneToOne
    private User user;

    @ManyToOne
    private Advertisement advertisement;

}

package com.example.hashcartapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likesId;

    private ZonedDateTime likesCreatedAt = ZonedDateTime.now(ZoneId.of("UTC"));

    @OneToOne
    private User user;

    @ManyToOne
    private Advertisement advertisement;

}

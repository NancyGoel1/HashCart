package com.example.hashcartapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    private String commentText;

    private ZonedDateTime commentCreationAt;

    @ManyToOne
    User user;

    @ManyToOne
    Advertisement advertisement;
}

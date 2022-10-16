package com.example.hashcartapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String name;

    private String role;

    @Column(unique = true)
    private Long empId;

    @Column(unique = true)
    private String email;

    @Column(length = 50)
    private String password;

    private String department;

    private String designation;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private List<Advertisement> advertisements;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private Likes likes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private List<Comment> comments;

}

package com.example.hashcartapp.dto;

import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.entities.Comment;
import com.example.hashcartapp.entities.Likes;
import lombok.Data;
import javax.validation.constraints.*;
import java.util.List;

@Data
public class UserDTO {

    public Long userId;

    @NotBlank(message = "Name is required")
    @Size(min=3, max=30, message = "Minimum length of name can be 3 and maximum can be 30")
    private String name;

    @NotBlank(message = "Role is required")
    private String role;

    @NotBlank(message = "employee id is required")
    private Long empId;

    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "password is required")
    private String department;

    @NotBlank(message = "designation is required")
    private String designation;

    private List<Advertisement> advertisementList;

    private Likes likes;

    private List<Comment> comments;


}

package com.example.hashcartapp.dto;

import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.entities.Comment;
import com.example.hashcartapp.entities.Likes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    private boolean isBanned=false;

    private List<Advertisement> advertisementList;

    private List<Likes> likes;

    private List<Comment> comments;

}

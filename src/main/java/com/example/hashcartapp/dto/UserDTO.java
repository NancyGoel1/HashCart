package com.example.hashcartapp.dto;

import com.example.hashcartapp.entities.Advertisement;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.Date;
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

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "password is required")
    private String department;

    @NotBlank(message = "designation is required")
    private String designation;

    private List<Advertisement> advertisementList;


}

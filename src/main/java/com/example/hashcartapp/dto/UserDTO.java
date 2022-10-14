package com.example.hashcartapp.dto;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UserAdvertisementDTO {

    private long userId;

    @NotBlank(message = "Name is required")
    @Size(min=3, max=30, message = "Minimum length of name can be 3 and maximum can be 30")
    private String name;

    @NotBlank(message = "Role is required")
    private String role;

    @NotBlank(message = "employee id is required")
    private long empId;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "password is required")
    private String department;

    @NotBlank(message = "designation is required")
    private String designation;

    private long advertisementId;

    private String description;

    private String type;

    private String category;

    private String location;

    private long priceRangeLower;

    private String priceRangeHigher;

    private Date creationDate;

    private Date closedDate;

}

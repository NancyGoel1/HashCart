package com.example.hashcartapp.service;

import com.example.hashcartapp.dto.UserDTO;
import com.example.hashcartapp.entities.User;

import java.util.List;

public interface UserService {

    public List<UserDTO> getAllUsers();

    public UserDTO getUserById(Long userId);

    public UserDTO saveUser(UserDTO userDTO);


    public UserDTO convertEntityToDTO(User user);
}

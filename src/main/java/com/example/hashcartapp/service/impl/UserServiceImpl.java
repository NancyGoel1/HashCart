package com.example.hashcartapp.service.impl;

import com.example.hashcartapp.controller.UserController;
import com.example.hashcartapp.dto.UserDTO;
import com.example.hashcartapp.entities.User;
import com.example.hashcartapp.repository.UserRepository;
import com.example.hashcartapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

     @Autowired
     UserRepository userRepository;

     @Autowired
     UserService userService;

     @Autowired
     ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    public List<UserDTO> getAllUsers(){
         return userRepository.findAll()
                 .stream()
                 .map(this::convertEntityToDTO)
                 .collect(Collectors.toList());
     }

    public UserDTO getUserById(Long userId) {
        UserDTO userToReturn = null;
         try {
             Optional<User> userFound = userRepository.findById(userId);

             if (userFound.isPresent()) {
                 userToReturn = userFound.map(this::convertEntityToDTO).get();
             }

         } catch (NoSuchElementException e) {
             logger.error(e.getMessage());
         }
         return userToReturn;
     }

     public UserDTO saveUser(UserDTO userDTO){

        User user = this.modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        return this.modelMapper.map(savedUser,UserDTO.class);
    }


     public UserDTO convertEntityToDTO(User user){
           UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
           return userDTO;
     }
}

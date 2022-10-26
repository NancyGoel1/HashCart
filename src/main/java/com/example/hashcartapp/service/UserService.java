package com.example.hashcartapp.service;

import com.example.hashcartapp.controller.AdvertisementController;
import com.example.hashcartapp.dto.UserDTO;
import com.example.hashcartapp.entities.User;
import com.example.hashcartapp.repository.UserRepository;
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
public class UserService {

     @Autowired
     UserRepository userRepository;

     @Autowired
     ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(AdvertisementController.class);

    public List<UserDTO> getAllUsers(){
         return userRepository.findAll()
                 .stream()
                 .map(this::convertEntityToDTO)
                 .collect(Collectors.toList());
     }

    public UserDTO getUserById(Long userId) {
//         if(userRepository.isUserBanned(userId).equals(true)) {
//             logger("This user is temporarily banned");
//         }
         UserDTO userToReturn = null;

         try {
             Optional<User> userFound = userRepository.findById(userId);

             if (userFound.isPresent()) {
                 userToReturn = userFound.map(this::convertEntityToDTO).get();
             }

         } catch (NoSuchElementException e) {
             logger.error(e.getMessage());
         } catch (IllegalArgumentException e1) {
             logger.error(e1.getMessage());
         } catch (Exception e2) {
             logger.error(e2.getMessage());
         }
         return userToReturn;
     }

     public User saveUser(User user){
        return userRepository.save(user);
     }


     public UserDTO convertEntityToDTO(User user){
           UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
           return userDTO;
     }
}

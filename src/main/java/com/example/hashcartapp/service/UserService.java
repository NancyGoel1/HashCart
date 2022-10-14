package com.example.hashcartapp.service;

import com.example.hashcartapp.dto.UserDTO;
import com.example.hashcartapp.entities.User;
import com.example.hashcartapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hibernate.internal.CoreLogging.logger;

@Service
public class UserService {

     @Autowired
     UserRepository userRepository;

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
             logger("No user found in the table having this id");
             e.printStackTrace();
         } catch (IllegalArgumentException e1) {
             e1.printStackTrace();
         } catch (Exception e2) {
             e2.printStackTrace();
         }
         return userToReturn;
     }

     public User saveUser(User user){
        return userRepository.save(user);
     }


     public UserDTO convertEntityToDTO(User user){
           UserDTO userDTO = new UserDTO();
           userDTO.setUserId(user.getUserId());
           userDTO.setName(user.getName());
           userDTO.setRole(user.getRole());
           userDTO.setEmpId(user.getEmpId());
           userDTO.setEmail(user.getEmail());
           userDTO.setPassword(user.getPassword());
           userDTO.setDepartment(user.getDepartment());
           userDTO.setDesignation(user.getDesignation());
           userDTO.setAdvertisementList(user.getAdvertisements());
           return userDTO;
     }

      /* public User DTOToEntity(UserDTO userdto) {
        User user= new User();
        user.setUserId(user.getUserId());
        user.setName(user.getName());
        user.setEmpId(user.getEmpId());
        user.setEmail(user.getEmail());
        user.setDepartment(user.getDepartment());
        user.setDesignation(user.getDesignation());
        user.setPassword(user.getPassword());
        user.setRole(user.getRole());
        return user;
    }*/
}

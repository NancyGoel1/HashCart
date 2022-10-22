package com.example.hashcartapp.service;

import com.example.hashcartapp.dto.UserDTO;
import com.example.hashcartapp.entities.User;
import com.example.hashcartapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
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

     @Autowired
     ModelMapper modelMapper;


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
           UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
           return userDTO;
     }
}

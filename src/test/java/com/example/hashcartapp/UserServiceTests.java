package com.example.hashcartapp;

import com.example.hashcartapp.entities.User;
import com.example.hashcartapp.repository.UserRepository;
import com.example.hashcartapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void given_get_all_users_should_return_list_of_all_users(){
        userService.getAllUsers();
        verify(userRepository,times(1)).findAll();
    }

    @Test
    public void given_id_then_should_return_user_of_that_id(){
      userService.getUserById(29L);
      verify(userRepository,times(1)).findById(29L);
    }

    @Test
    public void given_user_to_add_should_return_added_user(){
        User user = new User(1L,"Avi Jain","user", 456791L,"avi91@gmail.com","Jain!@34","Delivery","SDE 3");
        userService.saveUser(user);
        verify(userRepository,times(1)).save(user);
    }



}

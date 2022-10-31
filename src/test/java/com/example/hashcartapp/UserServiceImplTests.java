package com.example.hashcartapp;

import com.example.hashcartapp.dto.UserDTO;
import com.example.hashcartapp.entities.User;
import com.example.hashcartapp.repository.UserRepository;
import com.example.hashcartapp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    ModelMapper modelMapper;

    @Test
    public void given_get_all_users_should_return_list_of_all_users() {
        userServiceImpl.getAllUsers();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void given_id_then_should_return_user_of_that_id() {
        userServiceImpl.getUserById(29L);
        verify(userRepository, times(1)).findById(29L);
    }

    @Test
    public void given_user_to_add_should_return_added_user() {
        UserDTO userDTO = new UserDTO(1L, "Avi Jain", "user", 456791L, "avi91@gmail.com", "Jain!@34", "Delivery", "SDE 3", false, new ArrayList<>(), null, new ArrayList<>());
        when(modelMapper.map(Mockito.any(), eq(User.class))).thenReturn(new User());
        when(modelMapper.map(Mockito.any(), eq(UserDTO.class))).thenReturn(new UserDTO());
        userServiceImpl.saveUser(userDTO);
        verify(userRepository, times(1)).save(Mockito.any());
    }

}
package com.example.hashcartapp;

import com.example.hashcartapp.entities.User;
import com.example.hashcartapp.repository.UserRepository;
import com.example.hashcartapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;


  /* @Test
    public void getAllUserTest(){
         when(userRepository.findAll()).thenReturn(Stream.
                of(new User(21L, "Nancy Goel", "user", 574565L, "nancy34@gmail.com", "19$4", "Delivery", "SDE 1")).collect(Collectors.toList()));
        System.out.println(userService.getAllUsers().toString());
         //assertEquals(1,userService.getAllUsers().size());
    }

   @Test
    public void getUserTest(){
        Long userId= Long.valueOf(21);
        when(userRepository.findById(userId)).thenReturn(Stream.
                of(new User(21L, "Nancy Goel", "user", 574565L, "nancy34@gmail.com", "19$4", "Delivery", "SDE 1")).collect(Collectors.toList()));
        assertEquals(1,userService.getUserById(userId));
    }*/

    @Test
    public void saveUserTest(){
        User user = new User(1L,"Avi Jain","user", 456791L,"avi91@gmail.com","Jain!@34","Delivery","SDE 3");
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user,userService.saveUser(user));
    }


    /*@Test
    public void deleteUserTest(){
        User user = new User(1L,"Avi Jain","user", 456791L,"avi91@gmail.com","Jain!@34","Delivery","SDE 3");
        userRepository.delete(user);
        verify(userRepository,times(1)).delete(user);
    }*/

}

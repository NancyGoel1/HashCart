package com.example.hashcartapp.controller;

import com.example.hashcartapp.dto.UserDTO;
import com.example.hashcartapp.entities.User;
import com.example.hashcartapp.repository.UserRepository;
import com.example.hashcartapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/userById")
	public ResponseEntity<UserDTO> getUser(@RequestParam(value = "userId") Long userId) {
		UserDTO user = userService.getUserById(userId);

		if (user != null) {
			return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
		}

		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}

    /*@PostMapping("/user")
    public UserDTO registerUser(@RequestBody User user) {
            return this.userService.addNewUser(user);
    }*/

	@PostMapping("/user")
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
		try {
			User userSaved = userService.saveUser(user);

			if (userSaved != null) {
				return new ResponseEntity<User>(userSaved, HttpStatus.CREATED);
			}

			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}




/* @PostMapping(value = "/user")
    public String registerUser(@Valid @RequestBody User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userRepository.save(user);
            return "successfully registered";
        }
        catch (Exception e){
            e.printStackTrace();
            return "not registered";
        }
    }*/
}

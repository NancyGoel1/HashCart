package com.example.hashcartapp.controller;

import com.example.hashcartapp.dto.UserDTO;
import com.example.hashcartapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;

@RestController
public class UserController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

	Logger logger = LoggerFactory.getLogger(UserController.class);



	/**
	 * It shows details of all users
	 * @return It returns details of all the users
	 */
	@GetMapping("/user")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }


	/**
	 * It shows detail of a user on any particular id
	 * @param userId
	 * @return It returns the details of any particular user
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
		UserDTO user = userService.getUserById(userId);
		if (user != null) {
			return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
		}
		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}

	/**
	 * A new user can register
	 * @param userDTO
	 * @return It registers the new user
	 */
	@PostMapping("/signup")
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<UserDTO> registerUser( @RequestBody UserDTO userDTO) {
		try {
			userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			UserDTO userSaved = userService.saveUser(userDTO);
			if (userSaved != null) {
				logger.info("User saved successfully");
				return new ResponseEntity<UserDTO>(userSaved, HttpStatus.CREATED);
			}

			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		} catch (HttpClientErrorException e) {
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseEntity<UserDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

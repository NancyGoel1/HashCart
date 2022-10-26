package com.example.hashcartapp.controller;

import com.example.hashcartapp.dto.UserDTO;
import com.example.hashcartapp.entities.User;
import com.example.hashcartapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

	//@PreAuthorize("hasRole('admin')")
	/**
	 * It shows details of all users
	 * @return It returns details of all the users
	 */
	@GetMapping("/user")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }


	//@PreAuthorize("hasRole('user')")
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
	 * @param user
	 * @return It post the new user
	 */
	@PostMapping("/signup")
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User userSaved = userService.saveUser(user);

			if (userSaved != null) {
				return new ResponseEntity<User>(userSaved, HttpStatus.CREATED);
			}

			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		} catch (HttpClientErrorException e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

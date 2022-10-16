package com.example.hashcartapp.controller;
import com.example.hashcartapp.dto.UserDTO;
import com.example.hashcartapp.entities.User;
import com.example.hashcartapp.repository.UserRepository;
import com.example.hashcartapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

	//@PreAuthorize("hasRole('admin')")
    @GetMapping("/getUser")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }


	//@PreAuthorize("hasRole('user')")
	//@Secured({"user"})
	@GetMapping("/userById")
	public ResponseEntity<UserDTO> getUser(@Valid @RequestParam(value = "userId") Long userId) {
		UserDTO user = userService.getUserById(userId);

		if (user != null) {
			return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
		}

		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/signup")
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
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

}

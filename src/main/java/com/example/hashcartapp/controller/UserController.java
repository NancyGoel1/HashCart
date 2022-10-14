package com.example.Demo_func1.controller;

import com.example.Demo_func1.model.User;
import com.example.Demo_func1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @GetMapping("/userById")
    public User getUserById(@RequestParam(value = "user_id") long user_id){
        Optional<User> result= userRepository.findById(user_id);
        return (User)result.get();
    }

    @RequestMapping(value = "/SignUp",method = RequestMethod.POST)
    public String registerUser(@RequestBody User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userRepository.save(user);
            return "successfully registered";
        }
        catch (Exception e){
            e.printStackTrace();
            return "not registered";
        }
    }
}

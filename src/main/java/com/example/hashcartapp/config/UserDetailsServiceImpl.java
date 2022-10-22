package com.example.hashcartapp.config;

import com.example.hashcartapp.entities.User;
import com.example.hashcartapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByUserName(username);

        if(user==null){
            throw new UsernameNotFoundException("Couldn't find user");
        }

        UserDetails userDetails = new UserDetails(user);

        return userDetails;
    }}

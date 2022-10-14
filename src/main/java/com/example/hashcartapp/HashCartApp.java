package com.example.hashcartapp;

import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.repository.AdvertisementRepository;
import com.example.hashcartapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HashCartApp{

	public static void main(String[] args) {
		SpringApplication.run(HashCartApp.class, args);
	}


}

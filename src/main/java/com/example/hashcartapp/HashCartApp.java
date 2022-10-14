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

	/*@Autowired
	UserRepository userRepository;

	@Autowired
	AdvertisementRepository advertisementRepository;
	@Override
	public void run(String... args)throws Exception{

		Advertisement advertisement= new Advertisement();
		advertisement.setDescription("Watches to be sold");
        advertisement.setType("Sell");
		advertisement.setCategory("Electronics");
		advertisement.setLocation("Delhi");
		advertisement.setPriceRangeLower(2000);
		advertisement.setPriceRangeHigher(5000);
		//advertisement.setCreationDate(2022-10-11);
		//advertisement.setCreationDate(2022-10-11);
	}*/

}

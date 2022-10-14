package com.example.hashcartapp.controller;

import com.example.hashcartapp.dto.AdvertisementDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.repository.AdvertisementRepository;
import com.example.hashcartapp.service.AdvertisementService;
import com.example.hashcartapp.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AdvertisementController {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping("/advertisement")
    public List<AdvertisementDTO> getAdvertisements() {
        return advertisementService.getAllAdvertisements();
    }

    @GetMapping("/advertisementById")
    public ResponseEntity<AdvertisementDTO> getAdvertisementById(@RequestParam(value = "advertisementId") Long advertisementId){
        AdvertisementDTO advertisement= advertisementService.getAdvertisementById(advertisementId);
        if(advertisement!=null){
            return new ResponseEntity<AdvertisementDTO>(advertisement, HttpStatus.OK);
        }
        return new ResponseEntity<AdvertisementDTO>(HttpStatus.NOT_FOUND);
    }

    /*@PostMapping("/advertisement")
    public String postingAnAdvertisement(@Valid @RequestBody Advertisement advertisement) {
        try {
            this.advertisementRepository.save(advertisement);
            return "An advertisement is successfully registered";
        } catch (Exception e) {
            e.printStackTrace();
            return "not registered";
        }
    }*/

    @PostMapping("/advertisement")
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Advertisement> registerUser(@Valid @RequestBody Advertisement advertisement) {
		try {
			Advertisement  advertisementSaved = advertisementService.saveAdvertisement(advertisement);

			if (advertisementSaved != null) {
				return new ResponseEntity<Advertisement>(advertisementSaved, HttpStatus.CREATED);
			}

			return new ResponseEntity<Advertisement>(HttpStatus.CONFLICT);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<Advertisement>(HttpStatus.CONFLICT);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Advertisement>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

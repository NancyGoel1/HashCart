package com.example.hashcartapp.controller;

import com.example.hashcartapp.dto.AdvertisementDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.repository.AdvertisementRepository;
import com.example.hashcartapp.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import javax.validation.Valid;
import java.util.List;

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

	@Secured({"admin", "user"})
    @PostMapping("/postAdvertisement")
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Advertisement> registerAdvertisement(@Valid @RequestBody Advertisement advertisement) {
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

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
     @DeleteMapping("/advertisement")
	public ResponseEntity<Advertisement> deleteAdvertisement(@Valid @RequestParam(value = "advertisementId") Long advertisementId) {
		try {
			advertisementService.deleteAdvertisement(advertisementId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

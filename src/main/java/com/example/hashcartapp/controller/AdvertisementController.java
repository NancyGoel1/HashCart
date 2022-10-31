package com.example.hashcartapp.controller;

import com.example.hashcartapp.constants.Category;
import com.example.hashcartapp.constants.Location;
import com.example.hashcartapp.constants.Type;
import com.example.hashcartapp.dto.AdvertisementDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.service.AdvertisementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdvertisementController {
    @Autowired
    private AdvertisementService advertisementService;

	Logger logger = LoggerFactory.getLogger(AdvertisementController.class);

	/**
	 * It shows all the advertisements
	 * @return It returns details of all the advertisements
	 */
	@GetMapping("/advertisement")
    public List<AdvertisementDTO> getAdvertisements() {
        return advertisementService.getAllAdvertisements();
    }

	/**
	 * It shows the detail of the advertisement of any particular id
	 * @param advertisementId
	 * @return  It returns detail of the advertisement
	 */
	@GetMapping("/advertisement/{advertisementId}")
    public ResponseEntity<AdvertisementDTO> getAdvertisementById(@PathVariable Long advertisementId) {
		AdvertisementDTO advertisement = advertisementService.getAdvertisementById(advertisementId);
		if (advertisement != null) {
			return new ResponseEntity<AdvertisementDTO>(advertisement, HttpStatus.OK);
		}
		return new ResponseEntity<AdvertisementDTO>(HttpStatus.NOT_FOUND);
	}


	/**
	 * Show advertisement by category
	 * @param category category of advertisement can be VEHICLES, BOOKS etc
	 * @param page page no
	 * @param size size
	 * @return It returns all advertisement by category
	 */
	@GetMapping("/advertisement/category/{category}")
	public Page<Advertisement> findAdvertisementByCategory(@PathVariable("category") Category category,
														   @RequestParam(value = "page", defaultValue = "1") Integer page,
														   @RequestParam(value = "size", defaultValue = "3") Integer size) {
		PageRequest request = PageRequest.of(page - 1, size);
		return advertisementService.findAdvertisementByCategory(category,request);
	}


	/**
	 * Show advertisement by type
	 * @param type type of advertisement can be LOST, FOUND etc
	 * @param page page no
	 * @param size size
	 * @return It returns all advertisement by type
	 */
	@GetMapping("/advertisement/type/{type}")
	public Page<Advertisement> findAdvertisementByType(@PathVariable("type") Type type,
													   @RequestParam(value = "page", defaultValue = "1") Integer page,
													   @RequestParam(value = "size", defaultValue = "3") Integer size) {
		PageRequest request = PageRequest.of(page - 1, size);
		return advertisementService.findAdvertisementByType(type,request);
	}

	/**
	 * Show advertisement by location
	 * @param location location of advertisement
	 * @param page page no
	 * @param size size
	 * @return It returns all advertisement by location
	 */
	@GetMapping("/advertisement/location/{location}")
	public Page<Advertisement> findAdvertisementByLocation(@PathVariable("location") Location location,
														   @RequestParam(value = "page", defaultValue = "1") Integer page,
													       @RequestParam(value = "size", defaultValue = "3") Integer size) {
		PageRequest request = PageRequest.of(page - 1, size);
		return advertisementService.findAdvertisementByLocation(location,request);
	}


	/**
	 *  User is creating a new advertisement
	 * @param advertisementDTO
	 * @return User post any new advertisement
	 */
    @PostMapping("advertisement")
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<AdvertisementDTO> registerAdvertisement( @RequestBody AdvertisementDTO advertisementDTO){
		try {
			AdvertisementDTO  advertisementSaved = advertisementService.saveAdvertisement(advertisementDTO);
			if (advertisementSaved != null) {
				logger.info("Advertisement saved successfully");
				return new ResponseEntity<AdvertisementDTO>(advertisementSaved, HttpStatus.CREATED);
			}

			return new ResponseEntity<AdvertisementDTO>(HttpStatus.CONFLICT);
		} catch (HttpClientErrorException e) {
			logger.error("Exception occurred {}",e.getMessage());
			return new ResponseEntity<AdvertisementDTO>(HttpStatus.CONFLICT);
		}
		catch (Exception e) {
			return new ResponseEntity<AdvertisementDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * User is deleting the advertisement
	 * @param advertisementId
	 * @return It deletes the advertisement
	 */
     @DeleteMapping("/advertisement/{advertisementId}")
	public ResponseEntity<Advertisement> deleteAdvertisement(@Valid @PathVariable("advertisementId") Long advertisementId) {
		try {
			advertisementService.deleteAdvertisement(advertisementId);
			logger.info("Advertisement is deleted successfully");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 * User can change the details of the advertisement
	 * @param advertisement
	 * @param advertisementId
	 * @return It updates the advertisement
	 */

	@PutMapping("/advertisement/{advertisementId}")
	public AdvertisementDTO updateAdvertisement(@Valid @RequestBody AdvertisementDTO advertisement, @PathVariable("advertisementId") Long advertisementId) {
		AdvertisementDTO advertisementUpdated = null;

		try {
			advertisementUpdated= advertisementService.updateAdvertisement(advertisement, advertisementId);
		    logger.info("Advertisement is updated successfully");
		} catch (NullPointerException e1) {
			logger.error(e1.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return advertisementUpdated;
	}
}

package com.example.hashcartapp.controller;

import com.example.hashcartapp.dto.AdvertisementDTO;
import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.service.AdvertisementService;
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

	/**
	 *
	 * @return It returns details of all the advertisements
	 */
	@GetMapping("/advertisement")
    public List<AdvertisementDTO> getAdvertisements() {
        return advertisementService.getAllAdvertisements();
    }

	/**
	 *
	 * @param advertisementId
	 * @return  It returns the advertisement of any particular id
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
	 * @param page pageno
	 * @param size size
	 * @return Advertisement by category
	 */
	@GetMapping("/advertisementByCategory/{category}")
	public Page<Advertisement> findAdvertisementByCategory(@PathVariable("category") String category,
														   @RequestParam(value = "page", defaultValue = "1") Integer page,
														   @RequestParam(value = "size", defaultValue = "3") Integer size) {
		PageRequest request = PageRequest.of(page - 1, size);
		return advertisementService.findAdvertisementByCategory(category,request);
	}


	/**
	 *  Show advertisement by type
	 */
	@GetMapping("/advertisementByType/{type}")
	public Page<Advertisement> findAdvertisementByType(@PathVariable("type") String type,
													   @RequestParam(value = "page", defaultValue = "1") Integer page,
													   @RequestParam(value = "size", defaultValue = "3") Integer size) {
		PageRequest request = PageRequest.of(page - 1, size);
		return advertisementService.findAdvertisementByType(type,request);
	}

	/**
	 *  Show advertisement by location
	 */
	@GetMapping("/advertisementByLocation/{location}")
	public Page<Advertisement> findAdvertisementByLocation(@PathVariable("location") String location,
														   @RequestParam(value = "page", defaultValue = "1") Integer page,
													       @RequestParam(value = "size", defaultValue = "3") Integer size) {
		PageRequest request = PageRequest.of(page - 1, size);
		return advertisementService.findAdvertisementByLocation(location,request);
	}

//	@Secured({"admin", "user"})

	/**
	 *
	 * @param advertisement
	 * @return User post any new advertisement
	 */
    @PostMapping("/advertisement")
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Advertisement> registerAdvertisement( @RequestBody Advertisement advertisement) {
		try {
			Advertisement  advertisementSaved = advertisementService.saveAdvertisement(advertisement);
			if (advertisementSaved != null) {
				return new ResponseEntity<Advertisement>(advertisementSaved, HttpStatus.CREATED);
			}

			return new ResponseEntity<Advertisement>(HttpStatus.CONFLICT);
		} catch (HttpClientErrorException e) {
			return new ResponseEntity<Advertisement>(HttpStatus.CONFLICT);
		}
		catch (Exception e) {
			return new ResponseEntity<Advertisement>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    //@Secured({"admin", "user"})

	/**
	 *
	 * @param advertisementId
	 * @return It deletes the advertisement
	 */
     @DeleteMapping("/advertisement/{advertisementId}")
	public ResponseEntity<Advertisement> deleteAdvertisement(@Valid @PathVariable("advertisementId") Long advertisementId) {
		try {
			advertisementService.deleteAdvertisement(advertisementId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/advertisement/{advertisementId}")
	public AdvertisementDTO updateAdvertisement(@Valid @RequestBody AdvertisementDTO advertisement, @PathVariable("advertisementId") Long advertisementId) {
		AdvertisementDTO advertisementUpdated = null;

		try {
			advertisementUpdated= advertisementService.updateAdvertisement(advertisement, advertisementId);
		} catch (NullPointerException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return advertisementUpdated;
	}
}

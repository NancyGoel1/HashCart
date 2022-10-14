package com.example.Demo_func1.controller;

import com.example.Demo_func1.model.Advertisement;
import com.example.Demo_func1.model.User;
import com.example.Demo_func1.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AdvertisementController {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @GetMapping("/advertisement")
    public List<Advertisement> getAdvertisements() {
        return advertisementRepository.findAll();
    }

    @GetMapping("/advertisementById")
    public Advertisement getAdvertisementById(@RequestParam(value = "advertisement_id") long advertisement_id) {
        Optional<Advertisement> result = advertisementRepository.findById(advertisement_id);
        return (Advertisement) result.get();
    }

    @RequestMapping(value = "/postAnAdvertisement", method = RequestMethod.POST)
    public String postingAnAdvertisement(@RequestBody Advertisement advertisement) {
        try {
            this.advertisementRepository.save(advertisement);
            return "An advertisement is successfully registered";
        } catch (Exception e) {
            e.printStackTrace();
            return "not registered";
        }
    }
}

package com.project.TaskConnect.service;

import com.project.TaskConnect.model.Offer;
import com.project.TaskConnect.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    // Constructor injection for OfferRepository
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    // Fetch available cities (could be dynamic or static list)
    public List<String> getAvailableCities() {
        return Arrays.asList("Lahore", "Islamabad", "Karachi", "Rawalpindi");
    }

    // Fetch offers based on the selected city or all offers if no city is selected
    public List<Offer> getOffersByCity(String city) {
        if (city != null && !city.isEmpty()) {
            return offerRepository.findBycity(city);
        } else {
            return offerRepository.findAll();
        }
    }
}


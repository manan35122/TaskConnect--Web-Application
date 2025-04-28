package com.project.TaskConnect.repository;

import com.project.TaskConnect.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findBycity(String city);
    
    // Add this for debugging
    @Query("SELECT o FROM Offer o")
    List<Offer> findAll();
}
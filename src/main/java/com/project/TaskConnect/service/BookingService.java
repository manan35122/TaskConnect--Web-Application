package com.project.TaskConnect.service;

import com.project.TaskConnect.model.Booking;
import com.project.TaskConnect.repository.BookingRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Method to save the booking
    public Booking save(Booking booking) {
        System.out.println("Saving booking for user: " + booking.getEmail());
        if (booking.getBookingDate() == null) {
            booking.setBookingDate(LocalDateTime.now());  // Set the booking date if not already set
        }
        return bookingRepository.save(booking);  // Save booking to the database
    }
}

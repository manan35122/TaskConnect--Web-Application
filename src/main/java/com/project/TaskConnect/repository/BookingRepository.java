package com.project.TaskConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.TaskConnect.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // You can add custom queries for bookings if needed
}

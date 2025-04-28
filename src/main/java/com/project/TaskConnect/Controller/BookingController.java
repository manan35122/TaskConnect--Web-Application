package com.project.TaskConnect.Controller;

import com.project.TaskConnect.model.Booking;
import com.project.TaskConnect.model.User;
import com.project.TaskConnect.service.BookingService;
import com.project.TaskConnect.model.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Show the booking page and pre-fill the form with the logged-in user's information
    @GetMapping("/book")
    public String showBookingPage(Model model) {
        // Get current user from SessionManager (singleton)
        User currentUser = SessionManager.getInstance().getCurrentUser();
        
        if (currentUser != null) {
            System.out.println("Current logged-in user: " + currentUser.getEmail());
            // Add user details to the model for pre-filling the booking form
            model.addAttribute("name", currentUser.getFullname());
            model.addAttribute("email", currentUser.getEmail());
        } else {
            System.out.println("No user is currently logged in");
        }
        
        return "book";  // Return the booking page (book.html)
    }

    // Handle the booking form submission
    @PostMapping("/savebooking")
    public String saveBooking(@RequestParam String serviceType, 
            @RequestParam String preferredDate,
            @RequestParam String description,
            Model model) {
        
        // Get current user from SessionManager (singleton)
        User currentUser = SessionManager.getInstance().getCurrentUser();
        
        if (currentUser != null) {
            System.out.println("Saving booking for user: " + currentUser.getEmail());
            
            // Convert the preferredDate (String) to a LocalDate
            LocalDate parsedPreferredDate = LocalDate.parse(preferredDate);

            // Create a new booking using the constructor
            Booking booking = new Booking(
                    currentUser.getFullname(),
                    currentUser.getEmail(),
                    serviceType,
                    parsedPreferredDate,
                    description
                );
            bookingService.save(booking);  // Make sure the save method is implemented in BookingService

            System.out.println("Booking saved: Service Type - " + serviceType + ", Date - " + preferredDate);

            return "redirect:/dashboard";  
        } else {
            System.out.println("Cannot save booking, no user logged in");
            // Redirect to login or home if no user is logged in
            return "redirect:/home";
        }
    }
}

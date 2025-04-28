package com.project.TaskConnect.Controller;

import com.project.TaskConnect.model.Offer;
import com.project.TaskConnect.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class DashboardController {

    private final OfferService offerService;

    public DashboardController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(@RequestParam(required = false) String city, Model model) {
        List<String> cities = offerService.getAvailableCities();
        List<Offer> offers = offerService.getOffersByCity(city);

        model.addAttribute("cities", cities);
        model.addAttribute("offers", offers);
        model.addAttribute("selectedCity", city);

        return "dashboard"; 
    }

}

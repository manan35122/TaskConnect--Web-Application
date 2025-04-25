package com.project.TaskConnect.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Display Home Page as the first page
    @GetMapping("/")
    public String showHomePage() {
        return "home";  // This will load home.html
    }
    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup"; // This will load signup.html from templates
    }
    @GetMapping("/home")
    public String showPage() {
        return "home"; // This will load signup.html from templates
    }
}


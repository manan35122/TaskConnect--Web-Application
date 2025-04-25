package com.project.TaskConnect.Controller;

import com.project.TaskConnect.model.User;
import com.project.TaskConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
	
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {
        System.out.println("Login attempt for email: " + email); 
        User user = userService.login(email, password);        
        if (user != null) {
            model.addAttribute("user", user);
            return "dashboard"; // user-dashboard
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "home"; 
        }
    }
}
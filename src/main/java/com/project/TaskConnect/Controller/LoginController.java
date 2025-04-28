package com.project.TaskConnect.Controller;

import com.project.TaskConnect.model.User;
import com.project.TaskConnect.service.UserService;
import com.project.TaskConnect.model.SessionManager;
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
            // Set the current logged-in user in the session
            SessionManager.getInstance().setCurrentUser(user);
            System.out.println("User logged in successfully: " + user.getEmail());

            model.addAttribute("user", user);
            if (user.getType().equalsIgnoreCase("Customer")) {
                return "redirect:/dashboard";
            } else {
                return "pDashboard"; 
            }
        } else {
            System.out.println("Invalid email or password for: " + email);
            model.addAttribute("error", "Invalid email or password");
            return "home"; 
        }
    }
}

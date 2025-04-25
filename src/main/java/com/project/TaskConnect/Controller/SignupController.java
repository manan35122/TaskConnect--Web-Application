package com.project.TaskConnect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.project.TaskConnect.model.User;
import com.project.TaskConnect.service.UserService;

@Controller
public class SignupController {
	
	 @Autowired
	    private UserService userService;
	
	
	@PostMapping("/register")
    public String registerUser(@ModelAttribute User user, 
                               @RequestParam("confirmPassword") String confirmPassword, 
                               Model model) {

        // 1. Check if email exists using service
        String emailError = userService.validateEmail(user.getEmail());
        if (emailError != null) {
            model.addAttribute("emailError", emailError);
            return "signup"; // Redirect back to the registration form
        }

        // 2. Check if username exists using service
        String usernameError = userService.validateUsername(user.getUsername());
        if (usernameError != null) {
            model.addAttribute("usernameError", usernameError);
            return "signup"; // Redirect back to the registration form
        }

        // 3. Check if password and confirm password match using service
        String passwordError = userService.validatePasswords(user.getPassword(), confirmPassword);
        if (passwordError != null) {
            model.addAttribute("passwordError", passwordError);
            return "signup"; // Redirect back to the registration form
        }

        // If all validations pass, register the user
        userService.registerUser(user);
        
        return "redirect:/home"; 
    }

}

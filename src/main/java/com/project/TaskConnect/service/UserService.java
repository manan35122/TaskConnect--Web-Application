package com.project.TaskConnect.service;

import com.project.TaskConnect.model.User;
import com.project.TaskConnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String email, String password) {
    	 User user = userRepository.findByEmail(email);
    	    if (user != null) {
    	        System.out.println("User found: " + user.getEmail());
    	        if (user.getPassword().equals(password)) {
    	            return user;
    	        } else {
    	            System.out.println("Incorrect password for email: " + email);
    	        }
    	    } else {
    	        System.out.println("No user found with email: " + email);
    	    }
    	    return null;
    }
    
    public void registerUser(User user) {
        userRepository.save(user);
    }
    public String validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            return "Email already exists!";
        }
        return null; // No error
    }
    public String validateUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            return "Username already taken!";
        }
        return null; // No error
    }

    // 3. Check if password and confirm password match
    public String validatePasswords(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return "Passwords do not match!";
        }
        return null; // No error
    }
    
}

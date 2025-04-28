package com.project.TaskConnect.model;



public class SessionManager {
    
    private static SessionManager instance;
    private User currentUser;

    // Private constructor to prevent instantiation
    private SessionManager() {}

    // Get the singleton instance
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
            System.out.println("SessionManager instance created");
        }
        return instance;
    }

    // Get the current logged-in user
    public User getCurrentUser() {
        if (currentUser != null) {
            System.out.println("Fetching current user: " + currentUser.getEmail());
        } else {
            System.out.println("No current user set");
        }
        return currentUser;
    }

    // Set the current logged-in user
    public void setCurrentUser(User user) {
        this.currentUser = user;
        System.out.println("Current user set to: " + user.getEmail());
    }

    // Clear the session (used for logout)
    public void clearSession() {
        this.currentUser = null;
        System.out.println("Session cleared");
    }
}

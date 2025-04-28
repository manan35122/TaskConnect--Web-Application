package com.project.TaskConnect.Controller;

import com.project.TaskConnect.model.Booking;
import com.project.TaskConnect.model.SessionManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class JobController {

    private static final String URL = "jdbc:mysql://localhost:3306/taskconnect";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "manan35122";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
    }

    @GetMapping("/job")
    public String showJobsPage(Model model) {
        String username = SessionManager.getInstance().getCurrentUser().getEmail(); 
        System.out.println(username);
        List<Booking> jobs = new ArrayList<>();

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM booking WHERE email = ?");
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking job = new Booking();
                job.setId(rs.getLong("id"));
                job.setServiceType(rs.getString("service_type"));
                job.setDescription(rs.getString("description"));

                String status = rs.getString("status");
                if (status != null) {
                	job.setStatus(Booking.BookingStatus.valueOf(status.toUpperCase()));

                }
                job.setPreferredDate(rs.getObject("preferred_date", LocalDate.class));
                job.setBookingDate(rs.getObject("booking_date", LocalDateTime.class));
                jobs.add(job);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("jobs", jobs); 
        return "job"; 
    }
    @PostMapping("/job/complete")
    @ResponseBody
    public String completeJob(@RequestParam("id") Long jobId) {
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE booking SET status = ? WHERE id = ?");
            stmt.setString(1, "COMPLETED");
            stmt.setLong(2, jobId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
    
    @PostMapping("/job/cancel")
    @ResponseBody
    public String cancelJob(@RequestParam("id") Long jobId) {
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE booking SET status = ? WHERE id = ?");
            stmt.setString(1, "CANCELLED");
            stmt.setLong(2, jobId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
    
}

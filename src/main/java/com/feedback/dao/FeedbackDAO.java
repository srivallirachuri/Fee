package com.feedback.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.feedback.model.Feedback;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedbackDAO {
    private String dbUrl = "jdbc:mysql://localhost:3306/feedback_db";
    private String dbUname = "root"; // Your MySQL username
    private String dbPassword = "srivalli_16"; // Your MySQL password
    private String dbDriver = "com.mysql.cj.jdbc.Driver";
    

    public void loadDriver(String dbDriver) {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public String insert(Feedback feedback) {
        loadDriver(dbDriver);
        Connection con = getConnection();
        String result = "Data entered successfully";
        String sql = "INSERT INTO user_feedback (name, email, rating, comments) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, feedback.getName());
            ps.setString(2, feedback.getEmail());
            ps.setInt(3, feedback.getRating());
            ps.setString(4, feedback.getComments());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            result = "Data not entered";
        }
        return result;
    }
    
  
    public List<Feedback> getAllFeedback() {
        List<Feedback> list = new ArrayList<>();
        loadDriver(dbDriver);
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM user_feedback ORDER BY id DESC")) {
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback();
                f.setId(rs.getInt("id"));
                f.setName(rs.getString("name"));
                f.setEmail(rs.getString("email"));
                f.setRating(rs.getInt("rating"));
                f.setComments(rs.getString("comments"));
                list.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void deleteFeedback(int id) {
        loadDriver(dbDriver);
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM user_feedback WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    public boolean validateAdmin(String user, String pass) {
        boolean status = false;
        loadDriver(dbDriver);
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM admins WHERE username=? AND password=?")) {
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            status = rs.next(); // Returns true if a record is found
        } catch (Exception e) { e.printStackTrace(); }
        return status;
    }
    public boolean checkAdmin(String username, String password) {
        boolean isValid = false;
        // Ensure your dbDriver, dbUrl, etc., are defined in this class
        loadDriver(dbDriver); 
        
        String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";
        
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isValid = true; // Record found!
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }
    
    
    public Map<String, String> getFeedbackStats() {
        Map<String, String> stats = new HashMap<>();
        loadDriver(dbDriver);
        // Query to get count and average rating
        String sql = "SELECT COUNT(*) as total, AVG(rating) as average FROM user_feedback";
        
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                stats.put("total", String.valueOf(rs.getInt("total")));
                // Formatting to 1 decimal place (e.g., 4.5)
                stats.put("average", String.format("%.1f", rs.getDouble("average")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stats;
    }
}
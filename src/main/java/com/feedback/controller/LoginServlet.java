package com.feedback.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.feedback.dao.FeedbackDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Get parameters from the form
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        
        // 2. Debugging: Check console to see what is arriving
        System.out.println("DEBUG: User received: [" + user + "]");
        System.out.println("DEBUG: Password received: [" + pass + "]");

        // 3. Null check and Trimming
        if (user != null) user = user.trim();
        if (pass != null) pass = pass.trim();

        // 4. Validate against Database using DAO
        FeedbackDAO dao = new FeedbackDAO();
        
        // Ensure you have implemented checkAdmin in your FeedbackDAO class
        if (user != null && pass != null && dao.checkAdmin(user, pass)) { 
            
            // SUCCESS: Create a session to "remember" the admin
            HttpSession session = request.getSession();
            session.setAttribute("adminUser", user);
            
            // Redirect to the Dashboard Servlet
            response.sendRedirect("AdminDashboard");
            
        } else {
            // FAILURE: Send back to login with error message
            request.setAttribute("error", "Invalid Credentials! Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
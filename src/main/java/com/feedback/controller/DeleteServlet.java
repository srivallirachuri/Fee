package com.feedback.controller;

import java.io.IOException;

import com.feedback.dao.FeedbackDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        FeedbackDAO dao = new FeedbackDAO();
        dao.deleteFeedback(id); // Call the delete method we discussed earlier
        
        // Go back to the dashboard to show the updated list
        response.sendRedirect("AdminDashboard");
    }
}
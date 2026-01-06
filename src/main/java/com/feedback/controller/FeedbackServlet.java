package com.feedback.controller;

import java.io.IOException;

import com.feedback.dao.FeedbackDAO;
import com.feedback.model.Feedback;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Get data from form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comments = request.getParameter("comments");

        // 2. Wrap data in Model object
        Feedback feedback = new Feedback(name, email, rating, comments);

        // 3. Save to Database via DAO
        FeedbackDAO fDao = new FeedbackDAO();
        String result = fDao.insert(feedback);

        // 4. Redirect based on result
        if (result.contains("successfully")) {
            response.sendRedirect("success.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
package com.feedback.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.feedback.dao.FeedbackDAO;
import com.feedback.model.Feedback;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/AdminDashboard")
public class AdminServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    
	    HttpSession session = request.getSession(false);
	    if (session != null && session.getAttribute("adminUser") != null) {
	        FeedbackDAO dao = new FeedbackDAO();
	        
	        // Fetch existing feedback list
	        List<Feedback> feedbackList = dao.getAllFeedback();
	        // Fetch new stats map
	        Map<String, String> stats = dao.getFeedbackStats();
	        
	        request.setAttribute("feedbackList", feedbackList);
	        request.setAttribute("stats", stats); // Pass stats to JSP
	        
	        request.getRequestDispatcher("admin.jsp").forward(request, response);
	    } else {
	        response.sendRedirect("login.jsp");
	    }
	}
}
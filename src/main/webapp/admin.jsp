<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map, com.feedback.model.Feedback" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    
    <style>
        /* Main page background */
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            margin: 0;
            font-family: 'Segoe UI', Arial, sans-serif;
        }

        /* Centralized wrapper */
        .admin-wrapper {
            max-width: 1000px;
            margin: 0 auto;
            padding: 40px 20px;
        }

        /* Header section */
        .admin-header {
            text-align: center;
            color: white;
            margin-bottom: 30px;
        }

        .user-info {
        color:grey;
            margin-top: 10px;
            font-size: 0.9rem;
        }

        /* Stats Cards Row */
        .stats-container {
            display: flex;
            gap: 20px;
            margin-bottom: 25px;
        }

        .card {
            background: white;
            flex: 1;
            padding: 20px;
            border-radius: 12px;
            text-align: center;
            box-shadow: 0 4px 15px rgba(0,0,0,0.2);
        }

        .label {
            display: block;
            color: #888;
            font-size: 0.8rem;
            font-weight: bold;
            text-transform: uppercase;
        }

        .value {
            display: block;
            font-size: 2rem;
            color: #764ba2;
            font-weight: bold;
            margin-top: 5px;
        }

        /* Table container styling */
        .admin-container {
            background: white;
            padding: 0; /* Changed to 0 to let the purple header hit the edges */
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            overflow: hidden;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        thead th {
            background-color: #764ba2;
            color: white;
            padding: 18px;
            text-align: left;
            font-size: 0.9rem;
        }

        tbody td {
            padding: 15px 18px;
            border-bottom: 1px solid #f0f0f0;
            color: #333;
            vertical-align: middle;
        }

        tr:last-child td { border-bottom: none; }
        tr:hover { background-color: #f9f9f9; }

        .rating-star { color: #f1c40f; font-weight: bold; }
        
        .delete-link {
            color: #e74c3c;
            text-decoration: none;
            font-weight: bold;
            padding: 5px 10px;
            border: 1px solid #e74c3c;
            border-radius: 5px;
            font-size: 0.8rem;
            transition: 0.3s;
        }

        .delete-link:hover {
            background: #e74c3c;
            color: white;
        }
    </style>
</head>
<body>

    <div class="admin-wrapper">
        
        <div class="admin-header">
            <h1 style="color:grey;">Feedback Dashboard</h1>
            <div class="user-info">
                Welcome, <strong><%= session.getAttribute("adminUser") %></strong> 
                | <a href="LogoutServlet" style="color: red; text-decoration: none; font-weight: bold;">Logout</a>
            </div>
        </div>

        <% Map<String, String> stats = (Map<String, String>) request.getAttribute("stats"); %>
        <div class="stats-container">
            <div class="card">
                <span class="label">Total Feedbacks</span>
                <span class="value"><%= (stats != null) ? stats.get("total") : "0" %></span>
            </div>
            <div class="card">
                <span class="label">Average Rating</span>
                <span class="value">
                    <%= (stats != null) ? stats.get("average") : "0.0" %> 
                    <span style="color: #f1c40f;">★</span>
                </span>
            </div>
        </div>

        <div class="admin-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>User Details</th>
                        <th>Rating</th>
                        <th>Comments</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Feedback> list = (List<Feedback>) request.getAttribute("feedbackList");
                        if(list != null && !list.isEmpty()) {
                            for(Feedback f : list) {
                    %>
                    <tr>
                        <td style="color: #888;">#<%= f.getId() %></td>
                        <td>
                            <strong><%= f.getName() %></strong><br>
                            <small style="color: #777;"><%= f.getEmail() %></small>
                        </td>
                        <td class="rating-star"><%= f.getRating() %> ★</td>
                        <td style="font-style: italic; color: #555;"><%= f.getComments() %></td>
                        <td>
                            <a href="DeleteServlet?id=<%= f.getId() %>" 
                               class="delete-link"
                               onclick="return confirm('Are you sure you want to delete this feedback?')">
                               Delete
                            </a>
                        </td>
                    </tr>
                    <%      }
                        } else { 
                    %>
                    <tr>
                        <td colspan="5" style="text-align: center; padding: 40px; color: #999;">
                            No feedback available yet.
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        
    </div> </body>
</html>
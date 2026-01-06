<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="feedback-container">
        <h2>Admin Login</h2>
        <% if(request.getAttribute("error") != null) { %>
            <p style="color:red; text-align:center;"><%= request.getAttribute("error") %></p>
        <% } %>
        <form action="LoginServlet" method="post">
            <div class="form-group">
                <label>Username</label>
                <input type="text" name="username" placeholder="Enter admin" required>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" placeholder="Enter password" required>
            </div>
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>
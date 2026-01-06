<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Submission Error</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .error-card {
            background: white;
            padding: 40px;
            border-radius: 15px;
            text-align: center;
            border-top: 5px solid #e74c3c;
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
        }
        .error-card h2 { color: #e74c3c; }
    </style>
</head>
<body>
    <div class="error-card">
        <div style="font-size: 50px; margin-bottom: 20px;">❌</div>
        <h2>Oops! Something went wrong.</h2>
        <p>We encountered a technical issue while processing your feedback.</p>
        <p style="color: #666; font-size: 0.8rem; margin-top: 10px;">
            Error Details: <%= exception != null ? exception.getMessage() : "Database Connection Failed" %>
        </p>
        <br>
        <a href="feedback-form.jsp" class="success-link" style="color: #e74c3c;">Try Again</a>
    </div>
</body>
</html>
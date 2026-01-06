<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Submit Feedback</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="feedback-container">
    <h2>Simple Feedback System</h2>
    <form action="FeedbackServlet" method="post">
        <div class="form-group">
            <label>Full Name</label>
            <input type="text" name="name" placeholder="John Doe" required>
        </div>
        <div class="form-group">
            <label>Email Address</label>
            <input type="email" name="email" placeholder="john@example.com" required>
        </div>
       <div class="form-group">
    <label>Your Rating</label>
    <div class="star-rating">
        <input type="radio" id="star5" name="rating" value="5" /><label for="star5" title="5 stars">★★★★★</label>
        <input type="radio" id="star4" name="rating" value="4" /><label for="star4" title="4 stars">★★★★</label>
        <input type="radio" id="star3" name="rating" value="3" /><label for="star3" title="3 stars">★★★</label>
        <input type="radio" id="star2" name="rating" value="2" /><label for="star2" title="2 stars">★★</label>
        <input type="radio" id="star1" name="rating" value="1" /><label for="star1" title="1 star">★</label>
    </div>
</div>
        <div class="form-group">
            <label>Your Comments</label>
            <textarea name="comments" rows="4" placeholder="Tell us what you think..."></textarea>
        </div>
        <button type="submit">Send Feedback</button>
    </form>
</div>
</body>
</html>
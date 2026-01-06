Project Overview
The application consists of two main parts: the User-Facing Feedback Form and the Admin Dashboard
*User Side
* Interactive Star Rating:A modern UI component that allows users to select ratings (1-5 stars) using an interactive hover-and-click interface.
* Feedback Submission:Users can submit their Name, Email, and detailed comments securely.
* Success Feedback:Instant visual confirmation upon successful data submission.

*Admin Side
* Secure Authentication: A session-based login system that protects sensitive feedback data.
* Real-time Statistics: Instant summary cards showing the **Total Feedbacks** received and the **Average Rating** across all users.
* Management Dashboard: A clean, organized table allowing admins to read and manage entries.
* CRUD Operations: Ability to delete specific feedback records from the database directly through the UI.

Technologies Used:
* Frontend:JSP (JavaServer Pages), CSS3 (Flexbox & Grid), HTML5.
* Backend: Java Servlets (Jakarta EE).
* Database: MySQL.
* Architecture: MVC (Model-View-Controller) pattern for clean code separation.
* API/Connectivity: JDBC (Java Database Connectivity) for SQL operations.

  Project Architecture

The project follows the MVC (Model-View-Controller) pattern:
1.  Model: `Feedback.java` (POJO) and `FeedbackDAO.java` (Database logic).
2.  View: JSP files for the user form, login page, and admin dashboard.
3.  Controller: Servlets to handle feedback submission, admin login, and data deletion.

---

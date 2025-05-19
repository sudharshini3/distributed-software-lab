/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
/**
 *
 * @author thisa
 */
@WebServlet(urlPatterns = {"/loginServlet"})
public class LoginServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException {
String username = request.getParameter("username");
String password = request.getParameter("password");
try (Connection conn = DatabaseUtil.getConnection()) {
PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
stmt.setString(1, username);
stmt.setString(2, password);
ResultSet rs = stmt.executeQuery();
if (rs.next()) {
// Authentication successful, create a session
HttpSession session = request.getSession();
session.setAttribute("username", username);

Cookie userCookie = new Cookie("username", username);
userCookie.setMaxAge(60 * 60 * 24 * 7); // 1 week
response.addCookie(userCookie);
Cookie themeCookie = new Cookie("theme", "dark"); // Example theme preference
themeCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
response.addCookie(themeCookie);
// Redirect to stock management page
response.sendRedirect("stockManagement");
} else {
response.getWriter().println("Invalid credentials. Please try again.");
}
} catch (SQLException e) {
e.printStackTrace();
response.getWriter().println("Database error.");
}
}
}

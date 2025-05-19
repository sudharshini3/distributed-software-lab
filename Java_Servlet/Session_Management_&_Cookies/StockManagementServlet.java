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
@WebServlet("/stockManagement")
public class StockManagementServlet extends HttpServlet {
protected void doGet(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException {
HttpSession session = request.getSession(false);
if (session == null || session.getAttribute("username") == null) {
response.sendRedirect("login");
return;
}
try (Connection conn = DatabaseUtil.getConnection()) {
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM stock");
response.setContentType("text/html");
PrintWriter out = response.getWriter();
out.println("<h2>Stock List</h2>");
out.println("<table border='1'><tr><th>ProductName</th><th>Quantity</th><th>Price</th><th>Actions</th></tr>");
while (rs.next()) {
String productName = rs.getString("product_name");
int quantity = rs.getInt("quantity");
double price = rs.getDouble("price");
out.println("<tr><td>" + productName + "</td><td>" + quantity
+ "</td><td>" + price + "</td>");
out.println("<td><a href='updateStock?id=" + rs.getInt("id")
+ "'>Update</a> | <a href='deleteStock?id=" + rs.getInt("id") +
"'>Delete</a></td></tr>");
}
out.println("</table>");
} catch (SQLException e) {
e.printStackTrace();
response.getWriter().println("Database error.");
}
Cookie[] cookies = request.getCookies();
for (Cookie cookie : cookies) {
if ("theme".equals(cookie.getName())) {
String theme = cookie.getValue();
response.getWriter().println("Current theme: " + theme);
}
}
}
}

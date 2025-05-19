/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author thisa
 */
@WebServlet(urlPatterns = {"/DisplayProductsServlet"})
public class DisplayProductsServlet extends HttpServlet {
    private Connection getConnection() throws SQLException {
    try {
       String url = "jdbc:mysql://localhost:3306/stock_management?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "thisaru"; // replace with your database password
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(StockManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}
protected void doGet(HttpServletRequest request, HttpServletResponse
response)
throws ServletException, IOException {
response.setContentType("text/html");
PrintWriter out = response.getWriter();
try (Connection conn = getConnection()) {
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM stock");
out.println("<html><body><h1>Stock List</h1>");
while (rs.next()) {
out.println("<p>" + rs.getString("product_name") + ": " +
rs.getInt("quantity") + "</p>");
}
out.println("</body></html>");
} catch (SQLException e) {
e.printStackTrace();
out.println("<h1>Database Error</h1>");
}
}
}

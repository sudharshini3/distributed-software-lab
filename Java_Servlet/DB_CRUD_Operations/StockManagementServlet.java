import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/stockAction")
public class StockManagementServlet extends HttpServlet {
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
protected void doPost(HttpServletRequest request, HttpServletResponse
response)
throws ServletException, IOException {
String action = request.getParameter("action");
String productName = request.getParameter("product_name");
int quantity = Integer.parseInt(request.getParameter("quantity"));
try (Connection conn = getConnection()) {
switch(action) {
case "Add Product":
try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO stock (product_name, quantity) VALUES (?, ?)")) {
stmt.setString(1, productName);
stmt.setInt(2, quantity);
stmt.executeUpdate();

response.getWriter().write("<h1>Product Added Successfully</h1>");
}
break;

case "Update Product":
try (PreparedStatement stmt = conn.prepareStatement("UPDATE stock SET quantity = ? WHERE product_name= ?")) {
stmt.setInt(1, quantity);
stmt.setString(2, productName);

stmt.executeUpdate();

response.getWriter().write("<h1>Product Updated Successfully</h1>");
}
break;

case "Delete Product":
try (PreparedStatement stmt = conn.prepareStatement(
"DELETE FROM stock WHERE product_name = ?")) {
stmt.setString(1, productName);

stmt.executeUpdate();

response.getWriter().write("<h1>Product Deleted Successfully</h1>");
}
break;
default:
response.getWriter().write("<h1>Invalid Action</h1>");
}
} catch (SQLException e) {
  e.printStackTrace();
response.getWriter().write("<h1>Database Error: " +
e.getMessage() + "</h1>");
}
}
  
}

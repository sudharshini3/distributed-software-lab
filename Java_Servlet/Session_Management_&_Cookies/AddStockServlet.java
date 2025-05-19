
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.*;

@WebServlet("/addStock")
public class AddStockServlet extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException {
HttpSession session = request.getSession(false);
if (session == null || session.getAttribute("username") == null) {
response.sendRedirect("login");
return;
}
String productName = request.getParameter("product_name");
int quantity = Integer.parseInt(request.getParameter("quantity"));
double price = Double.parseDouble(request.getParameter("price"));
try (Connection conn = DatabaseUtil.getConnection()) {
PreparedStatement stmt = conn.prepareStatement("INSERT INTO stock(product_name, quantity, price) VALUES (?, ?, ?)");
stmt.setString(1, productName);
stmt.setInt(2, quantity);
stmt.setDouble(3, price);
stmt.executeUpdate();
response.sendRedirect("stockManagement");
} catch (SQLException e) {
e.printStackTrace();
response.getWriter().println("Database error.");
}
}
}

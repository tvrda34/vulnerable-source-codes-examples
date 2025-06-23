import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class SQLInjectionDemo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        PrintWriter out = response.getWriter();

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:users.db");
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                out.println("User: " + rs.getString("username"));
            }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}

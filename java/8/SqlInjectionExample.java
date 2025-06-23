import java.sql.*;
import java.util.Scanner;

public class SqlInjectionExample {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:sample.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to the database.");

                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();

                String sql = "SELECT * FROM users WHERE username = '" + username + "'";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                if (rs.next()) {
                    System.out.println("User found: " + rs.getString("username"));
                } else {
                    System.out.println("User not found.");
                }

                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}

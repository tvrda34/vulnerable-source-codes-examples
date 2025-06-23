import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class IDORDemo extends HttpServlet {
    private static final Map<String, String> users = Map.of(
        "1", "Alice",
        "2", "Bob",
        "3", "Charlie"
    );

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        PrintWriter out = response.getWriter();

        out.println("Profile: " + users.getOrDefault(id, "User not found"));
    }
}

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class HardcodedSecretDemo extends HttpServlet {
    private static final String SECRET = "TopSecret123"; // Hardcoded secret

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("The API secret is: " + SECRET);
    }
}

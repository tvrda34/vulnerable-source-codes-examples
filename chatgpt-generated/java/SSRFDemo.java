import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;

public class SSRFDemo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = request.getParameter("url");
        PrintWriter out = response.getWriter();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}

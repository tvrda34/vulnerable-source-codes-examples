import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class PathTraversalDemo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String file = request.getParameter("file");
        PrintWriter out = response.getWriter();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                out.println(line);
            }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class XSSDemo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comment = request.getParameter("comment");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>User Comment:</h2><p>" + comment + "</p>");
    }
}

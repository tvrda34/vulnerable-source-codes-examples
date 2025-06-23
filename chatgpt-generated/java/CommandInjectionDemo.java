import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class CommandInjectionDemo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String host = request.getParameter("host");
        PrintWriter out = response.getWriter();

        Process process = Runtime.getRuntime().exec("ping -c 1 " + host);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            out.println(line);
        }
    }
}

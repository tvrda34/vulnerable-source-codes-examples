import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

public class XXEDemo extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String xml = request.getReader().lines().reduce("", (a, b) -> a + b);
        PrintWriter out = response.getWriter();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setExpandEntityReferences(true); // XXE is enabled
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(xml)));
            out.println("Parsed XML: " + doc.getDocumentElement().getNodeName());
        } catch (Exception e) {
            out.println("Error parsing XML: " + e.getMessage());
        }
    }
}

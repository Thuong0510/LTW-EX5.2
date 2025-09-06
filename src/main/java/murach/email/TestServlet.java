package murach.email;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println("""
            <!doctype html><html><head><meta charset="utf-8"><title>Test</title></head>
            <body>
              <h1 style="text-align:center;color:red;">TestServlet Post</h1>
            </body></html>
        """);
    }
}

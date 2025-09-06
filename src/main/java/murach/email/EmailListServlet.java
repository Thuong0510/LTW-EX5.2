package murach.email;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import murach.business.User;
import murach.data.UserDB;

@WebServlet("/emailList")
public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "join";

        if ("add".equals(action)) {
            String firstName = request.getParameter("firstName");
            String lastName  = request.getParameter("lastName");
            String email     = request.getParameter("email");

            if (isBlank(firstName) || isBlank(lastName) || isBlank(email)) {
                request.setAttribute("message", "Nhập thiếu nội dung!");
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName",  lastName);
                request.setAttribute("email",     email);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            }

            User user = new User(firstName, lastName, email);
            UserDB.insert(user);

            HttpSession session = request.getSession();
            session.setAttribute("firstName", firstName);
            session.setAttribute("lastName",  lastName);
            session.setAttribute("email",     email);

            response.sendRedirect(request.getContextPath() + "/test");
            return;
        } else { // join hoặc mặc định
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

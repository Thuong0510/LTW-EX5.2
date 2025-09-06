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

        // Đảm bảo UTF-8 cho dữ liệu tiếng Việt
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "join";

        switch (action) {
            case "add" -> {
                String firstName = request.getParameter("firstName");
                String lastName  = request.getParameter("lastName");
                String email     = request.getParameter("email");

                // Validate đơn giản
                if (isBlank(firstName) || isBlank(lastName) || isBlank(email)) {
                    request.setAttribute("message", "Nhập thiếu nội dung!");
                    // Trả lại form kèm dữ liệu đã nhập (request scope)
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("lastName",  lastName);
                    request.setAttribute("email",     email);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    return;
                }

                // (Tùy ý) lưu DB
                User user = new User(firstName, lastName, email);
                UserDB.insert(user);

                // Lưu vào SESSION để khi Back vẫn còn dữ liệu
                HttpSession session = request.getSession();
                session.setAttribute("firstName", firstName);
                session.setAttribute("lastName",  lastName);
                session.setAttribute("email",     email);

                // PRG: Redirect sang trang đích (TestServlet)
                response.sendRedirect(request.getContextPath() + "/test");
            }

            case "join" -> {
                // Vào trang form
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

            default -> {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Cho GET rẽ về form
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}

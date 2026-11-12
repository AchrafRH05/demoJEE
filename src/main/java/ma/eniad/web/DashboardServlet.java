
package ma.eniad.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        String user = null;

        if (session != null) {
            user = (String) session.getAttribute("user");
        }

        request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp")
               .forward(request, response);
    }
}

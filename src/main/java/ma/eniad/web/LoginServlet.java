
package ma.eniad.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // Display the login page
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/login.jsp")
               .forward(request, response);
    }

    // Process the login form
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "password".equals(password)) {

            HttpSession session = request.getSession();

            session.setAttribute("user", username);

            response.sendRedirect(
                request.getContextPath() + "/dashboard"
            );

        } else {

            request.setAttribute("error", "Invalid username or password");
            request.setAttribute("username", username);

            request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                   .forward(request, response);
        }
    }
}

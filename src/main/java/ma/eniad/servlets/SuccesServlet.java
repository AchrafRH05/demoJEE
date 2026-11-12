
package ma.eniad.servlets;


import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/succes")
public class SuccesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        HttpSession session = req.getSession(false);

        String utilisateur = null;

        if (session != null) {
            utilisateur = (String) session.getAttribute("utilisateur");
        }

        if (utilisateur == null) {
            resp.sendRedirect(req.getContextPath() + "/index.html");
            return;
        }

        resp.setContentType("text/html;charset=UTF-8");

        resp.getWriter().println(
            "<h1>Bienvenue " + utilisateur + " !</h1>"
        );
    }
}

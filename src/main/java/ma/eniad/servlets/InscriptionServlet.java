
package ma.eniad.servlets;


import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        resp.sendRedirect(req.getContextPath() + "/index.html");
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");

        String nom = req.getParameter("nom");
        String email = req.getParameter("email");
        String ageStr = req.getParameter("age");

        if (nom == null || nom.trim().isEmpty()) {
            resp.sendRedirect("index.html?erreur=nom_vide");
            return;
        }

        if (email == null || !email.contains("@")) {
            resp.sendRedirect("index.html?erreur=email_invalide");
            return;
        }

        int age;

        try {
            age = Integer.parseInt(ageStr);

            if (age < 18 || age > 120) {
                resp.sendRedirect("index.html?erreur=age_invalide");
                return;
            }

        } catch (Exception e) {
            resp.sendRedirect("index.html?erreur=age_invalide");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("utilisateur", nom);

        resp.sendRedirect(req.getContextPath() + "/succes");
    }
}

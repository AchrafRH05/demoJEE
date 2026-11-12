
package ma.eniad.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import ma.eniad.model.ProduitDAO;

@WebListener
public class AppInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {

        ServletContext context = event.getServletContext();

        ProduitDAO produitDAO = new ProduitDAO();

        context.setAttribute("produitDAO", produitDAO);

        System.out.println("Application started: ProduitDAO initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("Application stopped.");
    }
}

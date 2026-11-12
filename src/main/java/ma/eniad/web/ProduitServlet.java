
package ma.eniad.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.eniad.model.Produit;
import ma.eniad.model.ProduitDAO;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {

    private ProduitDAO produitDAO;

    @Override
    public void init() throws ServletException {

        produitDAO = (ProduitDAO) getServletContext()
                .getAttribute("produitDAO");

        if (produitDAO == null) {
            throw new ServletException("ProduitDAO not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // Display all products
        if (action == null || action.equals("list")) {

            request.setAttribute("produits", produitDAO.findAll());

            request.getRequestDispatcher(
                    "/WEB-INF/views/produits/list.jsp"
            ).forward(request, response);

        }

        // Display an empty form
        else if (action.equals("new")) {

            request.setAttribute("produit", new Produit());

            request.getRequestDispatcher(
                    "/WEB-INF/views/produits/form.jsp"
            ).forward(request, response);

        }

        // Display the form with existing product information
        else if (action.equals("edit")) {

            try {
                Long id = Long.parseLong(request.getParameter("id"));

                Produit produit = produitDAO.findById(id);

                if (produit == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }

                request.setAttribute("produit", produit);

                request.getRequestDispatcher(
                        "/WEB-INF/views/produits/form.jsp"
                ).forward(request, response);

            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

        }

        else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // DELETE PRODUCT
        if ("delete".equals(action)) {

            try {
                Long id = Long.parseLong(request.getParameter("id"));

                produitDAO.delete(id);

                request.getSession().setAttribute(
                        "message", "Product deleted successfully!"
                );

                response.sendRedirect(
                        request.getContextPath() + "/produits"
                );

            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

            return;
        }

        // CREATE OR UPDATE PRODUCT
        String idParam = request.getParameter("id");
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String prixParam = request.getParameter("prix");
        String stockParam = request.getParameter("stock");

        java.util.Map<String, String> erreurs = new java.util.HashMap<>();

        // Validate name
        if (nom == null || nom.trim().length() < 2
                || nom.trim().length() > 50) {
            erreurs.put("nom", "Name must contain between 2 and 50 characters.");
        }

        // Validate description
        if (description != null && description.length() > 255) {
            erreurs.put("description", "Description must not exceed 255 characters.");
        }

        // Validate price
        java.math.BigDecimal prix = null;

        try {
            prix = new java.math.BigDecimal(prixParam.trim());

            if (prix.compareTo(java.math.BigDecimal.ZERO) <= 0) {
                erreurs.put("prix", "Price must be greater than zero.");
            }

        } catch (Exception e) {
            erreurs.put("prix", "Please enter a valid price.");
        }

        // Validate stock
        int stock = 0;

        try {
            if (stockParam != null && !stockParam.trim().isEmpty()) {
                stock = Integer.parseInt(stockParam.trim());
            }

            if (stock < 0) {
                erreurs.put("stock", "Stock cannot be negative.");
            }

        } catch (NumberFormatException e) {
            erreurs.put("stock", "Stock must be a valid integer.");
        }

        // If validation fails, return to the form
        Produit produit = new Produit();

        try {
            if (idParam != null && !idParam.trim().isEmpty()) {
                produit.setId(Long.parseLong(idParam));
            }
        } catch (NumberFormatException e) {
            erreurs.put("id", "Invalid product ID.");
        }

        produit.setNom(nom == null ? "" : nom.trim());
        produit.setDescription(description == null ? "" : description.trim());
        produit.setPrix(prix);
        produit.setStock(stock);

        if (!erreurs.isEmpty()) {

            request.setAttribute("erreurs", erreurs);
            request.setAttribute("produit", produit);

            request.getRequestDispatcher(
                    "/WEB-INF/views/produits/form.jsp"
            ).forward(request, response);

            return;
        }

        // Save product
        produitDAO.save(produit);

        request.getSession().setAttribute(
                "message", "Product saved successfully!"
        );

        response.sendRedirect(
                request.getContextPath() + "/produits"
        );
    }

}

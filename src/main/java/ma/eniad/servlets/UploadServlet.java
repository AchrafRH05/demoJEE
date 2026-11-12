
package ma.eniad.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException, ServletException {

        Part filePart = req.getPart("file");

        if (filePart == null ||
            filePart.getSize() == 0) {

            resp.getWriter().println("Aucun fichier sélectionné");
            return;
        }

        String fileName = Paths.get(
            filePart.getSubmittedFileName()
        ).getFileName().toString();

        String uploadPath = getServletContext()
            .getRealPath("/uploads");

        File directory = new File(uploadPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        filePart.write(
            new File(directory, fileName).getAbsolutePath()
        );

        resp.setContentType("text/html;charset=UTF-8");

        resp.getWriter().println(
            "<h1>Fichier uploadé : " + fileName + "</h1>"
        );
    }
}

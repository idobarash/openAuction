package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Servlet to map pictures from physical place on the hard-disk.
 * JSF can load only pictures that are alive with in it's resource
 * folder in the webapp. If we need an external image we have
 * to map it via this servlet.
 */
@WebServlet("/pics/*")
public class ImagesServlet extends HttpServlet {

    private static final String PATH = "/data/openu/images/items/";

    /**
     * Get the image
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get by id or by file name

        String itemId = request.getParameter("itemId");
        String filename = "";
        if (itemId == null || itemId.isEmpty() == true) {
            filename = request.getPathInfo().substring(1);
        } else {
            filename = itemId + ".jpg";
        }

        File file = new File(PATH, filename);
        if (file.exists() == false) {
            file = new File(PATH, "placeholder.png");
        }

        response.setHeader("Content-Type", getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");

        Files.copy(file.toPath(), response.getOutputStream());
    }
}

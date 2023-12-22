package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exemple")
public class DemoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private List<FormResponse> formResponses = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String deleteId = request.getParameter("delete_id");

        if (deleteId != null) {
           
            for (FormResponse formResponse : formResponses) {
                if (formResponse.getId().equals(deleteId)) {
                    formResponses.remove(formResponse);
                    break;
                }
            }
        }

        out.println("<html><head><title>Réponses du formulaire</title>");
        out.println("<style>");
        out.println(".pageTitle { color: black; font-size: 24px; }"); // Style pour la classe pageTitle
        out.println(".response { background-color: #f0f0f0; margin: 10px; padding: 10px; }"); // Style pour la classe response
        out.println(".deleteButton { background-color: red; color: white; padding: 5px 10px; text-decoration: none; margin-left: 10px; }"); // Style pour les boutons de suppression
        out.println(".submitButton { background-color: green; color: white; padding: 5px 10px; text-decoration: none; margin-left: 10px; }"); // Style pour les boutons de formulaire
        out.println("</style>");
        out.println("</head><body>");
        out.println("<h1 class=\"pageTitle\">Réponses du formulaire</h1>");

        for (FormResponse formResponse : formResponses) {
            out.println("<div class=\"response\">");
            out.println("<p>ID : " + formResponse.getId() + "</p>");
            out.println("<p>Summary: " + formResponse.getSummary() + "</p>");
            out.println("<p>Description: " + formResponse.getDescription() + "</p>");
            out.println("<a href=\"/demo/exemple?delete_id=" + formResponse.getId() + "\" class=\"deleteButton\">Supprimer</a>");
            out.println("</div>");
        }

        out.println("<a href=\"/demo/form\" class=\"submitButton\">Formulaire</a>");
        out.println("</body></html>");

    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String summary = request.getParameter("summary");
        String description = request.getParameter("description");

        FormResponse formResponse = new FormResponse(id, summary, description);
        formResponses.add(formResponse);

        response.sendRedirect("/demo/exemple");
    }
}

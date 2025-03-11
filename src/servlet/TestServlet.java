package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Employe;
import dao.CompteurDAO;
import dao.DemandeDAO;
import dao.EmployeDAO;

import java.io.IOException;

@WebServlet("/Test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employe utilisateur = (Employe) session.getAttribute("utilisateur");
        if (utilisateur != null) {
            try {
                request.setAttribute("compteurDAO", new CompteurDAO());
                request.setAttribute("employeDAO", new EmployeDAO());
                request.setAttribute("demandeDAO", new DemandeDAO());
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.getServletContext().getRequestDispatcher("/vues/Test.jsp").forward(request, response);
        } else {
            response.sendRedirect("Connexion");
        }
    }
}
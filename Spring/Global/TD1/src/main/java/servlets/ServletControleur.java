package servlets;

import modele.Membre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet(name = "ServletControleur", urlPatterns = "/Controleur/*")
public class ServletControleur extends HttpServlet {

    String action;
    Collection<Membre> membres;

    @Override
    public void init(){
        Membre m1 = new Membre("Paul","1234","Popol");
        Membre m2 = new Membre("Maxime","1234","Max");
        Membre m3 = new Membre("Anna","1234","Nana");
        membres = new ArrayList<Membre>();
        membres.add(m1);
        membres.add(m2);
        membres.add(m3);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("resp", true);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        for (Membre membre: membres) {
            if(login.equals(membre.getLogin())){
                if(password.equals(membre.getMotdepasse())){
                    request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
                }
            }
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("resp", false);
        this.action = request.getPathInfo();
        if(this.action == null){
            request.getRequestDispatcher("/WEB-INF/default.jsp").forward(request,response);
        }
        else{
            switch (this.action){
                case "/login":
                    request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                    break;
                case "/home":
                    request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("/WEB-INF/default.jsp").forward(request,response);
                    break;
            }
        }

    }
}

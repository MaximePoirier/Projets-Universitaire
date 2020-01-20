package servlets;


import modele.Competence;
import modele.CompetenceMembre;
import modele.Membre;
import modele.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.ControleService;
import services.FacadeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet(name = "Controleur", urlPatterns = "/")
public class Controleur extends HttpServlet {

    @Autowired
    private FacadeService facadeServiceImpl;
    @Autowired
    private ControleService controleServiceImpl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String todo = request.getParameter("TODO");

        if ((request.getSession().getAttribute("util")==null) && ((todo == null) || (todo.equals("selogguer")==false))) {
            request.getRequestDispatcher("/WEB-INF/accueil.jsp").
                    forward(request, response);
        } else {
            if (todo == null) {
                menu(request, response);

            } else {
                switch (todo) {
                    case "selogguer":
                        String log = request.getParameter("login");
                        String mdp = request.getParameter("mdp");
                        for (Membre m : facadeServiceImpl.getMembres()) {
                            if (m.getLogin().equals(log) &&
                                    m.getMotdepasse().equals(mdp)) {
                                // le login est mis en session pour s'en souvenir...
                                request.getSession().setAttribute("util", log);
                                menu(request, response);
                                break;
                            }
                        }
                        request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
                        break;

                    case "participer":
                        Membre membre = facadeServiceImpl.getMembreFromLogin((String) request.getSession().getAttribute("util"));
                        Projet projet = facadeServiceImpl.getProjetFromIntituleP(request.getParameter("intituleP"));
                        if ((projet.getContributionDe().contains(membre) == false) && (projet.getDirigePar() != membre)) {
                            projet.getContributionDe().add(membre);
                            membre.getParticipe().add(projet);
                        }
                        menu(request, response);

                        break;

                    case "competence":
                        Membre membre1 = facadeServiceImpl.getMembreFromLogin((String) request.getSession().getAttribute("util"));
                        Competence competence = facadeServiceImpl.getCompetenceFromIntituleC(request.getParameter("intituleC"));
                        if(facadeServiceImpl.getAutreComp(membre1).contains(competence)){
                            String niv = (String) request.getParameter("niveau");
                            int niveau = Integer.parseInt(niv);
                            CompetenceMembre cm = new CompetenceMembre(membre1,competence,niveau,(String) request.getParameter("commentaire"));
                            membre1.getDeclare().add(cm);
                            competence.getCompMembre().add(cm);
                        }
                        menu(request, response);

                    case "delogger":
                        request.getSession().setAttribute("util",null);
                        request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);

                    case "projet":
                        Membre membre2 = facadeServiceImpl.getMembreFromLogin((String) request.getSession().getAttribute("util"));
                        Projet p = new Projet(request.getParameter("nom"),request.getParameter("description"));
                        p.setDirigePar(membre2);
                        membre2.getResponsable().add(p);
                        String[] checked = request.getParameterValues("competences[]");
                        Collection<Competence> compsReq = new ArrayList<>();
                        if (checked != null){
                            for (String comp : checked){
                                Competence c = facadeServiceImpl.getCompetenceFromIntituleC(comp);
                                c.getRequisePour().add(p);
                                compsReq.add(c);
                            }
                        }
                        p.getNecessite().addAll(compsReq);
                        facadeServiceImpl.getProjets().add(p);
                        menu(request,response);

                    default:
                        request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
                }
            }
        }
    }

    private void menu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Membre membre= facadeServiceImpl.getMembreFromLogin((String)request.getSession().getAttribute("util"));

        // on affiche la page de menu, en lui passant le membre qui vient de se logguer
        request.setAttribute("courant",membre);

        Collection<Projet> pcomp=facadeServiceImpl.competences(membre);
        request.setAttribute("competent",pcomp);
        request.setAttribute("autres",facadeServiceImpl.autres(membre,pcomp));
        request.setAttribute("checkBase",controleServiceImpl.checkBase());
        request.setAttribute("autreCompetence",facadeServiceImpl.getAutreComp(membre));
        request.setAttribute("AllCompetence",facadeServiceImpl.getCompetences());

        request.getRequestDispatcher("/WEB-INF/menu.jsp").forward(request,response);
    }

}










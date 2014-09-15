package Controlleurs;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modeles.Entreprise;
import Modeles.Utilisateur;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getAttribute("message") == null) {
			request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("login") != " " && request.getParameter("mdp") != " " && request.getParameter("entreprise") != " ") {
			String id_utilisateur;
			String id_entreprise;
			Entreprise entreprise = null;
			try {
				id_utilisateur = Utilisateur.lastId();
				id_entreprise = Entreprise.lastId();
				Utilisateur user = new Utilisateur(
						id_utilisateur,
						request.getParameter("login"), 
						request.getParameter("mdp"),
						"0");
				
				try {
					Utilisateur.addUserWithoutEntreprise(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				entreprise = new Entreprise(id_entreprise, id_utilisateur, request.getParameter("entreprise"));
				Entreprise.addEntreprise(entreprise);
				
				user.setId_entreprise(id_entreprise);
				user.setEntreprise();
				Utilisateur.updateUser(user);
				
				request.setAttribute("user", user);
				doGet(request, response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}

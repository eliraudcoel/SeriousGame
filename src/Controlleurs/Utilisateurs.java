package Controlleurs;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modeles.Entreprise;
import Modeles.Utilisateur;

/**
 * Servlet implementation class Utilisateurs
 */
@WebServlet("/Utilisateurs")
public class Utilisateurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Utilisateurs() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action") != null) {
			if(request.getParameter("action").equals("edit"))
				request.getRequestDispatcher("WEB-INF/utilisateurs/edit.jsp").forward(request, response);
		}
		if(request.getAttribute("action") != null) {
			if(request.getAttribute("action").equals("edit"))
				request.getRequestDispatcher("WEB-INF/utilisateurs/edit.jsp").forward(request, response);
		}
	}
	
	protected boolean verifMdp(String mdp, String mdp_confirmation) {
		boolean ok = false;
		
		if( mdp == mdp_confirmation)
			ok = true;
		
		return ok;
	}
	
	protected boolean verifEntreprise(String nom_entreprise) throws SQLException {
		boolean ok = false;
		Entreprise entreprise = Entreprise.find_by_name(nom_entreprise);
		
		if (entreprise == null)
			ok = true;
		
		return ok;
	}
	
	protected void updateUserInSession(HttpServletRequest request) throws SQLException {
		Utilisateur user = Utilisateur.find(request.getParameter("user_id"));
		HttpSession session = request.getSession();
		
		session.removeAttribute("user");
		session.setAttribute("user", user);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message_mdp = "", message_ent = "";
		boolean mdp_ok = false;
		boolean ent_ok = false;
		Utilisateur user = null;
		Entreprise entreprise = null;
		
		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");
		String mdp_confirmation = request.getParameter("mdp_confirmation");
		String nom_entreprise = request.getParameter("entreprise");
		
		try {
			user = Utilisateur.find(request.getParameter("user_id"));
			entreprise = user.getEntreprise();
			
			if(mdp != "" && mdp_confirmation != "") {
				mdp_ok = verifMdp(mdp, mdp_confirmation);
				if(mdp_ok)
					user.update_by_params("mdp", mdp);
				else
					message_mdp = "Vous devez remplir le mot de passe et la confirmation";
			}
			if(login != "") {
				user.update_by_params("login", login);
			}
			if(!user.is_admin()) {
				if(nom_entreprise != "") {
					ent_ok = verifEntreprise(nom_entreprise);
					if(ent_ok)
						entreprise.update_by_params("nom_entreprise", nom_entreprise);
					else
						message_ent = "Le nom d'entreprise que vous avez rentrer est déjà utilisé";
				}
				if (message_ent != "")
					request.setAttribute("message_ent", message_ent);
			}
			
			if (message_mdp != "")
				request.setAttribute("message_mdp", message_mdp);
			
			if (message_mdp == "")
				request.setAttribute("message_ok", "Modifications enregistrées!");
			
			updateUserInSession(request);
		} catch (SQLException e) {
			e.getMessage();
		}
		
		request.setAttribute("action", "edit");
		doGet(request, response);
	}
}

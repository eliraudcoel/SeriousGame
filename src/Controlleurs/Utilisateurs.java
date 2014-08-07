package Controlleurs;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		if( request.getParameter("action") != null)
			if(request.getParameter("action").equals("edit"))
				request.getRequestDispatcher("WEB-INF/utilisateurs/edit.jsp").forward(request, response);
		if (request.getAttribute("action") != null)
			if(request.getAttribute("action").equals("edit"))
				request.getRequestDispatcher("WEB-INF/utilisateurs/edit.jsp").forward(request, response);
	}
	
	protected boolean verifMdp(String mdp, String mdp_confirmation) {
		boolean ok = false;
		
		if( mdp == mdp_confirmation)
			ok = true;
		
		return ok;
	}
	
	protected boolean verifEmail(String email, Utilisateur current_user) throws SQLException {
		boolean ok = false;
		Utilisateur user = Utilisateur.find_by_email(email);
		
		if (user != current_user || user == null)
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
		String message_mdp = "", message_email = "";
		boolean mdp_ok = false;
		boolean email_ok = false;
		Utilisateur user = null;
		
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");
		String mdp_confirmation = request.getParameter("mdp_confirmation");
		
		try {
			user = Utilisateur.find(request.getParameter("user_id"));
			
			if(mdp != "" && mdp_confirmation != "") {
				mdp_ok = verifMdp(mdp, mdp_confirmation);
				if(mdp_ok)
					user.update_by_params("mdp", mdp);
				else
					message_mdp = "Vous devez remplir le mot de passe et la confirmation";
			}
			
			if(email != "") {
				try {
					email_ok = verifEmail(email, user);
					if(email_ok)
						user.update_by_params("email", email);
					else
						message_email = "L'email que vous avez rentrer est déjà utilisé";
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if(login != "") {
				user.update_by_params("login", login);
			}
			
			if (message_email != "")
				request.setAttribute("message_email", message_email);
			if (message_mdp != "")
				request.setAttribute("message_mdp", message_mdp);
			
			updateUserInSession(request);
			
			request.setAttribute("action", "edit");
			
			doGet(request, response);
		} catch (SQLException e) {
			e.getMessage();
		}		
	}

}

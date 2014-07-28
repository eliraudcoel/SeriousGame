package Controlleurs;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modeles.Utilisateur;

/**
 * Servlet implementation class Connection
 */
@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connection() {
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
			request.getRequestDispatcher("sign_in.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		if(request.getParameter("login") != " " && request.getParameter("mdp") != " ") {
			try {
				Utilisateur user = Utilisateur.find_by_identification(request.getParameter("login"),request.getParameter("mdp"));
				if (user != null){
					request.setAttribute("user", user);
				}else {
					request.setAttribute("message", "votre mot de passe ou login est faux");
				}
				doGet(request, response);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}

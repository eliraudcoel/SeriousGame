package Controlleurs;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modeles.Partie;
import Modeles.Utilisateur;


/**
 * Servlet implementation class Parties
 */
@WebServlet("/Parties")
public class Parties extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Parties() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("index") || request.getAttribute("message") != null){
			List<Partie> in_processing_parties;
			List<Partie> not_started_parties;
			try {
				in_processing_parties = Partie.in_processing_partie();
				not_started_parties = Partie.not_started_partie();
				
				request.setAttribute("in_processing_parties", in_processing_parties);
				request.setAttribute("not_started_parties", not_started_parties);
				if( request.getAttribute("message") != null)
					request.setAttribute("message", request.getAttribute("message"));
				
				request.getRequestDispatcher("WEB-INF/parties/index.jsp").forward(request, response);
			} catch (SQLException e) {
				e.getMessage();
			} catch (ParseException e) {
				e.getMessage();
			}
		}
		if(request.getParameter("action").equals("show")){
			try {
				Partie partie = Partie.find(request.getParameter("nb"));
				request.setAttribute("partie", partie);
				
				request.getRequestDispatcher("WEB-INF/parties/show.jsp").forward(request, response);
			} catch (SQLException e) {
				e.getMessage();
			}
		}
		
		if(request.getParameter("action").equals("historic")){
			List<Partie> finished_parties;
			try {
				finished_parties = Partie.finished_partie();
			
				request.setAttribute("finished_parties", finished_parties);
				if( request.getAttribute("message") != null)
					request.setAttribute("message", request.getAttribute("message"));
				
				request.getRequestDispatcher("WEB-INF/parties/historic.jsp").forward(request, response);
			} catch (SQLException e) {
				e.getMessage();
			} catch (ParseException e) {
				e.getMessage();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("partie_id") != " " && request.getParameter("user_id") != " ") {
			Partie partie;
			Utilisateur user;
			
			try {
				user = (Utilisateur) Utilisateur.find(request.getParameter("user_id"));
				partie = Partie.find(request.getParameter("partie_id"));
				System.out.println("PARTIE :"+ partie.getNom_partie());
				partie.add_user(user);
				
				request.setAttribute("message", "Participation enregistrée!");
				doGet(request, response);
			} catch (SQLException e) {
				e.getMessage();
			}
		}
	}

}

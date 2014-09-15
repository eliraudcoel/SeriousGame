package Controlleurs;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modeles.Participation;
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
		List<Partie> parties = null;
		try {
			parties = Partie.all();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(request.getParameter("action") != null) {
			if(request.getParameter("action").equals("index")){
				List<Partie> in_processing_parties;
				List<Partie> not_started_parties;
				try {
					in_processing_parties = Partie.in_processing_partie(parties);
					not_started_parties = Partie.not_started_partie(parties);
					
					request.setAttribute("in_processing_parties", in_processing_parties);
					request.setAttribute("not_started_parties", not_started_parties);
					if( request.getAttribute("message") != null)
						request.setAttribute("message", request.getAttribute("message"));					
				} catch (SQLException e) {
					e.getMessage();
				} catch (ParseException e) {
					e.getMessage();
				}
				request.getRequestDispatcher("WEB-INF/parties/index.jsp").forward(request, response);
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
			if(request.getParameter("action").equals("show_finish")){
				try {
					Partie partie = Partie.find(request.getParameter("nb"));
					request.setAttribute("partie", partie);
					
					request.getRequestDispatcher("WEB-INF/parties/show_finish.jsp").forward(request, response);
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if(request.getParameter("action").equals("new")){
				request.getRequestDispatcher("WEB-INF/parties/new.jsp").forward(request, response);
			}
			if(request.getParameter("action").equals("historic")){
				List<Partie> finished_parties;
				try {
					finished_parties = Partie.finished_partie(parties);
				
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
		if(request.getAttribute("message") != null) {
			List<Partie> in_processing_parties;
			List<Partie> not_started_parties;
			try {
				in_processing_parties = Partie.in_processing_partie(parties);
				not_started_parties = Partie.not_started_partie(parties);
				
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("type_post").contains("participer")) {
			String user_id = request.getParameter("user_id");
			if(request.getParameter("partie_id") != " " && user_id != " ") {
				Partie partie = null;
				Utilisateur user = null;
				try {
					user = (Utilisateur) Utilisateur.find(user_id);
					partie = Partie.find(request.getParameter("partie_id"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Participation.addParticipant(user, partie);
					request.setAttribute("message", "Participation enregistrée!");
				} catch (SQLException e) {
					e.getMessage();
				}
				doGet(request, response);
			}
		}
		if(request.getParameter("type_post").contains("ajouter")) {
			String nom_partie = request.getParameter("nom_partie"), duree = request.getParameter("duree"),
					date_deb = request.getParameter("date_deb"), heure_deb = request.getParameter("heure_deb"),
					capital = request.getParameter("capital_dep"), cout_salaire = request.getParameter("cout_salaire"),
					cout_charge_exp = request.getParameter("cout_char_exp"), cout_loyer = request.getParameter("cout_loyer");
			
			String[] result = date_deb.split("-");
			
			date_deb = result[2] + "/" + result[1] + "/" + result[0];
			
			String msg = "";
			if(nom_partie != " " && duree != " " && date_deb != " " && heure_deb != " ") {
				Partie partie;
				String id_partie;
				try {
					id_partie = Partie.lastId();
					partie = new Partie(id_partie, nom_partie, duree, capital, cout_salaire, cout_charge_exp, cout_loyer, date_deb+" "+heure_deb);
					partie.add_partie();
					msg = "Partie enregistrée";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				msg = "Vous devez remplir tous les éléments avec une (*)";
			}
			
			request.setAttribute("message", msg);
			doGet(request, response);
		}
	}

}

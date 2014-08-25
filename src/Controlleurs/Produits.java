package Controlleurs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modeles.Evenement;
import Modeles.Utilisateur;

/**
 * Servlet implementation class Produits
 */
@WebServlet("/Produits")
public class Produits extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Produits() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println(request.getParameter("bois_prix_achat"));
		System.out.println(request.getParameter("bois_qte_achat"));
		System.out.println(request.getParameter("bois_prix_vente"));
		System.out.println(request.getParameter("bois_qte_vente"));
		
		// Rendre le noms dynamiques selon les produits de l'utilisateur
		// Faire lastId() pour les evenements
		// Créer les != produits
		// Finir la logique
		
		
		if(request.getParameter("bois_prix_achat") != " " && request.getParameter("bois_qte_achat") != " "
				&& request.getParameter("bois_prix_vente") != " " && request.getParameter("bois_qte_vente") != " ") {
			
			Utilisateur user = (Utilisateur) request.getAttribute("user");
			Evenement evenement_achat = new Evenement("", "", user.getId(), "Achat", request.getParameter("bois_qte_achat"), request.getParameter("bois_prix_achat"));
			Evenement evenement_vente = new Evenement("", "", user.getId(), "Vente", request.getParameter("bois_qte_vente"), request.getParameter("bois_prix_vente"));
		}
	}

}

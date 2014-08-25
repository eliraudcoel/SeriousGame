package Controlleurs;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modeles.Entreprise;
import Modeles.Evenement;
import Modeles.Produit;
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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getAttribute("message") != null){
			// TODO A FIXER
			request.getRequestDispatcher("Parties?action=index").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur user;
		int error = 0;
		try {
			user = Utilisateur.find(request.getParameter("user_id"));
			Entreprise ent = (Entreprise) user.getEntreprise();
			ArrayList<Produit> produits = (ArrayList<Produit>) ent.getProduits();
			
			for (Produit produit : produits) {
				String nom_pd = produit.getNom_produit();
				if(request.getParameter(nom_pd+"_prix_achat") != " " && request.getParameter(nom_pd+"_qte_achat") != " "
						&& request.getParameter(nom_pd+"_prix_vente") != " " && request.getParameter(nom_pd+"_qte_achat") != " ") {
					int last_id = 0, next_last_id = 0;
					try {
						last_id = Integer.parseInt(Evenement.lastId());
						next_last_id = last_id + 1;
					} catch (NumberFormatException | SQLException e) {
						e.printStackTrace();
					}
					
					Evenement evenement_achat = new Evenement(""+last_id, produit.getId_produit(), user.getId(), "Achat", 
							request.getParameter(nom_pd+"_qte_achat"), request.getParameter(nom_pd+"_prix_achat"));
					Evenement evenement_vente = new Evenement(""+next_last_id, produit.getId_produit(), user.getId(), "Vente", 
							request.getParameter(nom_pd+"_qte_vente"), request.getParameter(nom_pd+"_prix_vente"));
					try {
						Evenement.addEvenement(evenement_achat);
						Evenement.addEvenement(evenement_vente);
						
					} catch (SQLException e) {
						error =+ 1;
						e.printStackTrace();
					}
				}
			}
			if(error == 0)
				request.setAttribute("message", "Evenements enregistrées!");
			doGet(request, response);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}

package Controlleurs;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modeles.Associer;
import Modeles.Contenir;
import Modeles.Entreprise;
import Modeles.Evenement;
import Modeles.Partie;
import Modeles.Produit;
import Modeles.Tour;
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
		response.sendRedirect("Parties?action=index");
	}
	
	
	public void addTourForPlayer(Utilisateur user, Tour tour, Partie partie, float total) throws SQLException {
		float user_capital = 0;
		// A FINIR
		if(tour.getNum_tour() == "1") {
			user_capital = Integer.parseInt(partie.getCapital_depart()) - total;
		} else {
			Associer last_associer = Associer.find_by_entreprise(Associer.lastIdByEntreprise(user.getId_entreprise()), tour.getId_tour());
			user_capital = Integer.parseInt(last_associer.getCapital_actuel());
		}
		Associer associer = new Associer(tour.getId_tour(), user.getId_entreprise(), ""+user_capital);
		Associer.addAssocier(associer);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur user;
		int error = 0;
		try {
			user = Utilisateur.find(request.getParameter("user_id"));
			Partie partie = Partie.find(request.getParameter("partie_id"));
			Entreprise ent = (Entreprise) user.getEntreprise();
			ArrayList<Produit> produits = (ArrayList<Produit>) ent.getProduits();
			Tour tour = Tour.last_tour_of_partie(partie);
			int last_id = 0, next_last_id = 0;
			float total = 0, total_vente = 0, total_achat = 0;
			
			for (Produit produit : produits) {
				String nom_pd = produit.getNom_produit();
				System.out.println("PRIX ACHAT : "+request.getParameter(nom_pd+"_prix_achat"));
				System.out.println("QTE ACHAT : "+request.getParameter(nom_pd+"_qte_achat"));
				System.out.println("PRIX VENTE : "+request.getParameter(nom_pd+"_prix_vente"));
				System.out.println("QTE VENTE : "+request.getParameter(nom_pd+"_qte_vente"));
				if(request.getParameter(nom_pd+"_prix_achat") != "0" && request.getParameter(nom_pd+"_qte_achat") != "0"
						&& request.getParameter(nom_pd+"_prix_vente") != "0" && request.getParameter(nom_pd+"_qte_achat") != "0") {
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
					
					// ERREUR -- tjr == 0
					
					Contenir contenir_achat = new Contenir(tour.getId_tour(), ""+last_id);
					Contenir contenir_vente = new Contenir(tour.getId_tour(), ""+next_last_id);
					try {
						Evenement.addEvenement(evenement_achat);
						Evenement.addEvenement(evenement_vente);
						Contenir.addContenir(contenir_achat);
						Contenir.addContenir(contenir_vente);
					} catch (SQLException e) {
						error =+ 1;
						e.printStackTrace();
					}
					
					total_achat = Float.parseFloat(request.getParameter(nom_pd+"_qte_achat")) * Float.parseFloat(request.getParameter(nom_pd+"_prix_achat"));
					total_vente = Float.parseFloat(request.getParameter(nom_pd+"_qte_vente")) * Float.parseFloat(request.getParameter(nom_pd+"_prix_vente"));
					total += total_vente - total_achat;
				}
			}
			if(error == 0) {
				addTourForPlayer(user, tour, partie, total);
				request.setAttribute("message", "Evenements enregistrées!");
			}
			doGet(request, response);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}

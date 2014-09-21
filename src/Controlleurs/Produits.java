package Controlleurs;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
	
	
	public void addTourForPlayer(String type, Utilisateur user, Tour tour, Partie partie, float total) throws SQLException {
		float user_capital = 0;		
		if(tour.getNum_tour().equals("1")) {
			user_capital = Float.parseFloat(partie.getCapital_depart()) - total;
		} else {
			Associer last_associer = Associer.find_by_entreprise(Associer.lastIdByEntreprise(user.getId_entreprise()), tour.getId_tour());
			user_capital = Float.parseFloat(last_associer.getCapital_actuel());
		}
		Associer associer = new Associer(tour.getId_tour(), user.getId_entreprise(), ""+user_capital);
		if(type == "Ajout") {
			Associer.addAssocier(associer);
		} else {
			Associer.updateAssocier(associer);
		}
	}

	public void addEvenementAdmin(Utilisateur user, Partie partie, HttpServletRequest request) throws SQLException {
		ArrayList<Utilisateur> users = (ArrayList<Utilisateur>) partie.get_all_utilisateurs();
		for(Utilisateur partie_user : users) {
			Entreprise ent = (Entreprise) partie_user.getEntreprise();
			ArrayList<Produit> produits = (ArrayList<Produit>) ent.getProduits();
			Tour tour = Tour.last_tour_of_partie(partie);
			int last_id = 0, next_last_id = 0;
			float total = 0, total_vente = 0, total_achat = 0;
			ArrayList<Evenement> user_evenements = (ArrayList<Evenement>) Evenement.find_by_utilisateur(partie_user.getId(), tour.getId_tour());
			
			for (Produit produit : produits) {
				String nom_pd = produit.getNom_produit();
	
				if(request.getParameter(partie_user.getLogin()+"_"+nom_pd+"_prix_achat") != "0"	&& request.getParameter(partie_user.getLogin()+"_"+nom_pd+"_prix_vente") != "0") {
					try {
						last_id = Integer.parseInt(Evenement.lastId());
						next_last_id = last_id + 1;
					} catch (NumberFormatException | SQLException e) {
						e.printStackTrace();
					}
					
					Evenement evenement_achat = new Evenement(""+last_id, produit.getId_produit(), partie_user.getId(), "Achat", 
							"0", request.getParameter(partie_user.getLogin()+"_"+nom_pd+"_prix_achat"));
					Evenement evenement_vente = new Evenement(""+next_last_id, produit.getId_produit(), partie_user.getId(), "Vente", 
							"0", request.getParameter(partie_user.getLogin()+"_"+nom_pd+"_prix_vente"));
					
					Contenir contenir_achat = new Contenir(tour.getId_tour(), ""+last_id);
					Contenir contenir_vente = new Contenir(tour.getId_tour(), ""+next_last_id);
					
					try {
						Evenement.addEvenement(evenement_achat);
						Evenement.addEvenement(evenement_vente);
						//Contenir.addContenir(contenir_achat);
						//Contenir.addContenir(contenir_vente);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					for(Evenement user_evenement : user_evenements) {
						if(user_evenement.getId_produit().equals(produit.getId_produit())) {
							if(user_evenement.getType_evenement().equals("Achat")) {
								total_achat =  Integer.parseInt(user_evenement.getQuantite()) * Float.parseFloat(request.getParameter(partie_user.getLogin()+"_"+nom_pd+"_prix_achat"));
							} else {
								total_vente = Integer.parseInt(user_evenement.getQuantite()) * Float.parseFloat(request.getParameter(partie_user.getLogin()+"_"+nom_pd+"_prix_vente"));
							}
						}
					}
					
					total = total + total_vente - total_achat;
				}
				addTourForPlayer("Modifié", partie_user, tour, partie, total);
			}
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur user;
		try {
			user = Utilisateur.find(request.getParameter("user_id"));
			Partie partie = Partie.find(request.getParameter("partie_id"));
			Tour tour = Tour.last_tour_of_partie(partie);
			int last_id = 0, next_last_id = 0;
			float total = 0, total_vente = 0, total_achat = 0;
			
			if(user.is_admin()) {
				addEvenementAdmin(user, partie, request);
				int id_new_tour = Integer.parseInt(tour.getId_tour()) + 1;
				int new_tour = Integer.parseInt(tour.getNum_tour()) + 1;
				if(partie.getDuree().equals(""+new_tour)) {
					Date today = new Date();
					partie.update_by_params("date_fin", ""+today);
				} else {
					Tour nouveau_tour = new Tour(""+id_new_tour, partie.getId_partie(), ""+new_tour, request.getParameter("regle"), "1");
					Tour.add_tour(nouveau_tour);
				}
			} else {
				Entreprise ent = (Entreprise) user.getEntreprise();
				ArrayList<Produit> produits = (ArrayList<Produit>) ent.getProduits();
				for (Produit produit : produits) {
					String nom_pd = produit.getNom_produit();
	
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
						
						Contenir contenir_achat = new Contenir(tour.getId_tour(), ""+last_id);
						Contenir contenir_vente = new Contenir(tour.getId_tour(), ""+next_last_id);
						try {
							Evenement.addEvenement(evenement_achat);
							Evenement.addEvenement(evenement_vente);
							Contenir.addContenir(contenir_achat);
							Contenir.addContenir(contenir_vente);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						total_achat = Float.parseFloat(request.getParameter(nom_pd+"_qte_achat")) * Float.parseFloat(request.getParameter(nom_pd+"_prix_achat"));
						total_vente = Float.parseFloat(request.getParameter(nom_pd+"_qte_vente")) * Float.parseFloat(request.getParameter(nom_pd+"_prix_vente"));
						total = total + total_vente - total_achat;
					}
				}
				addTourForPlayer("Ajout", user, tour, partie, total);
			}
			
			doGet(request, response);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}

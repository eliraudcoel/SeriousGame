package Modeles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Evenement extends Modele {
	
	private String id_evenement;
	private String id_produit;
	private String id_utilisateur;
	private String type_evenement;
	private String quantite;
	private String prix;
	
	public Evenement(String id_evenement, String id_produit,
			String id_utilisateur, String type_evenement, String quantite,
			String prix) {
		super();
		this.id_evenement = id_evenement;
		this.id_produit = id_produit;
		this.id_utilisateur = id_utilisateur;
		this.type_evenement = type_evenement;
		this.quantite = quantite;
		this.prix = prix;
	}
	
	public static List<Evenement> all() throws SQLException {
		List<Evenement> results = new ArrayList<Evenement>();
		
		ResultSet resultat = query( "SELECT id_evenement, id_produit, id_utilisateur, type_evenement, quantite, prix FROM evenement");
		while( resultat.next() ) {
			Evenement evenement = new Evenement(
					resultat.getString( "id_evenement" ), 
					resultat.getString( "id_produit" ), 
					resultat.getString( "id_utilisateur" ),
					resultat.getString( "type_evenement" ),
					resultat.getString( "quantite" ),
					resultat.getString( "prix" )
			);
			results.add(evenement);
		}
		return results;
	}
	
	public static String lastId() throws SQLException {
		String id = "";
		int ident = 0;
		ResultSet resultat = query( "SELECT max(id_evenement) max_id FROM evenement");
		
		while( resultat.next() ) {
			id = resultat.getString("max_id");
		}
		ident = Integer.parseInt(id);
		ident++;
		return ""+ident;
	}
	
	public static ArrayList<Evenement> find_by_utilisateur(String id_utilisateur, String id_tour) throws SQLException {
		Evenement evenement = null;
		ArrayList<Evenement> evenements = new ArrayList<Evenement>();
		ResultSet resultat = query( "SELECT ev.id_evenement as IdEv , ev.id_produit as IdPd, ev.id_utilisateur as IdUt, " +
				"ev.type_evenement as TpEv, ev.quantite as Qte, ev.prix as Px, c.ID_EVENEMENT , c.ID_TOUR FROM evenement ev, contenir c " +
				"WHERE ev.ID_EVENEMENT = c.ID_EVENEMENT AND ev.id_utilisateur='"+ id_utilisateur+"' AND c.id_tour ='"+id_tour+"'");
		
		while( resultat.next() ) {
			evenement = new Evenement(
					resultat.getString( "IdEv" ), 
					resultat.getString( "IdPd" ), 
					resultat.getString( "IdUt" ),
					resultat.getString( "TpEv" ),
					resultat.getString( "Qte" ),
					resultat.getString( "Px" )
			);
			evenements.add(evenement);
		}
		return evenements;
	}
	
	public static void addEvenement(Evenement ev) throws SQLException {
		update("INSERT into evenement VALUES('"+ev.getId_evenement()+"', '"+ev.getId_produit()+"', '"+ev.getId_utilisateur()+"','"
				+ev.getType_evenement()+"', '"+ev.getQuantite()+"', "+ev.getPrix()+")");
	}
	
	public String getId_evenement() {
		return id_evenement;
	}
	public void setId_evenement(String id_evenement) {
		this.id_evenement = id_evenement;
	}
	public String getId_produit() {
		return id_produit;
	}
	public void setId_produit(String id_produit) {
		this.id_produit = id_produit;
	}
	public String getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(String id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	public String getType_evenement() {
		return type_evenement;
	}
	public void setType_evenement(String type_evenement) {
		this.type_evenement = type_evenement;
	}
	public String getQuantite() {
		return quantite;
	}
	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
}

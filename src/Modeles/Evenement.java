package Modeles;

import java.sql.SQLException;

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
	
	public static void addEvenement(Evenement ev) throws SQLException {
		update("INSERT into evenement VALUES('"+ev.getId_evenement()+"', '"+ev.getId_produit()+"', '"+ev.getId_utilisateur()+"', '"+ev.getType_evenement()+"', '"+ev.getQuantite()+"', '"+ev.getPrix()+"')");
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

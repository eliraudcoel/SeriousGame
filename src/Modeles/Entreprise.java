package Modeles;

import java.sql.SQLException;

public class Entreprise {
	
	private String id_entreprise;
	private String id_utilisateur;
	private String nom_entreprise;
	private String capital;
	private Utilisateur utilisateur;
	
	public Entreprise(String id_entreprise, String id_utilisateur,
			String nom_entreprise, String capital) throws SQLException {
		super();
		this.id_entreprise = id_entreprise;
		this.id_utilisateur = id_utilisateur;
		this.nom_entreprise = nom_entreprise;
		this.capital = capital;
		this.utilisateur = Utilisateur.find(id_utilisateur);
	}
	
	public String getId_entreprise() {
		return id_entreprise;
	}
	public void setId_entreprise(String id_entreprise) {
		this.id_entreprise = id_entreprise;
	}
	public String getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(String id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	public String getNom_entreprise() {
		return nom_entreprise;
	}
	public void setNom_entreprise(String nom_entreprise) {
		this.nom_entreprise = nom_entreprise;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
}

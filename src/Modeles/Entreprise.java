package Modeles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Entreprise extends Modele {
	
	private String id_entreprise;
	private String id_utilisateur;
	private String nom_entreprise;
	private Utilisateur utilisateur;
	
	public Entreprise(String id_entreprise, String id_utilisateur,
			String nom_entreprise) throws SQLException {
		super();
		this.id_entreprise = id_entreprise;
		this.id_utilisateur = id_utilisateur;
		this.nom_entreprise = nom_entreprise;
		this.utilisateur = Utilisateur.find(id_utilisateur);
	}
	
	public static Entreprise find(String id_entreprise) throws SQLException {
		Entreprise entreprise = null;
		
		// Requête en BDD
		ResultSet resultat = query( "SELECT id_entreprise, id_utilisateur, nom_entreprise " +
				"FROM entreprise WHERE id_entreprise ='"+ id_entreprise +"'");
		
		// Tant qu'il y a des resultats on créé un object Pays
		if( resultat.next() ) {
			entreprise = new Entreprise(
					resultat.getString( "id_entreprise" ), 
					resultat.getString( "id_utilisateur" ),
					resultat.getString( "nom_entreprise" )
			);
		}
		return entreprise;
	}
	
	public static Entreprise find_by_user(String id_utilisateur) throws SQLException {
		Entreprise entreprise = null;
		
		// Requête en BDD
		ResultSet resultat = query( "SELECT id_entreprise, id_utilisateur, nom_entreprise " +
				"FROM entreprise WHERE id_utilisateur ='"+ id_utilisateur +"'");
		
		// Tant qu'il y a des resultats on créé un object Pays
		if( resultat.next() ) {
			entreprise = new Entreprise(
					resultat.getString( "id_entreprise" ), 
					resultat.getString( "id_utilisateur" ), 
					resultat.getString( "nom_entreprise" )
			);
		}
		return entreprise;
	}
	
	public static List<Entreprise> all() throws SQLException {
		List<Entreprise> results = new ArrayList<Entreprise>();
		
		ResultSet resultat = query( "SELECT id_entreprise,id_utilisateur, nom_entreprise FROM entreprise");
		while( resultat.next() ) {
			Entreprise entreprise = new Entreprise(
					resultat.getString( "id_entreprise" ), 
					resultat.getString( "id_utilisateur" ), 
					resultat.getString( "nom_entreprise" )
			);
			results.add(entreprise);
		}
		return results;
	}
	
	public static String lastId() throws SQLException {
		String id = "";
		List<Entreprise> entreprises = Entreprise.all();
		
		for (Entreprise entreprise : entreprises) {
			id = entreprise.getId_entreprise();
		}
		int ident = Integer.parseInt(id +1);
		return Integer.toString(ident);
	}
	
	public static void addEntreprise(Entreprise entreprise) throws SQLException {
		update("INSERT into entreprise VALUES('"+entreprise.getId_entreprise()+"','"+entreprise.getId_utilisateur()+"','"+entreprise.getNom_entreprise()+"')");
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
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
}

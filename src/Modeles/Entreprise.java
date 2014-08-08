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
	private List<Produit> produits;
	
	public Entreprise(String id_entreprise, String id_utilisateur,
			String nom_entreprise) throws SQLException {
		super();
		this.id_entreprise = id_entreprise;
		this.id_utilisateur = id_utilisateur;
		this.nom_entreprise = nom_entreprise;
		this.utilisateur = Utilisateur.find(id_utilisateur);
		this.produits = Produit.find_by_entreprise(id_entreprise);
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
		
		ResultSet resultat = query( "SELECT id_entreprise, id_utilisateur, nom_entreprise " +
				"FROM entreprise WHERE id_utilisateur ='"+ id_utilisateur +"'");
		
		if( resultat.next() ) {
			entreprise = new Entreprise(
					resultat.getString( "id_entreprise" ), 
					resultat.getString( "id_utilisateur" ), 
					resultat.getString( "nom_entreprise" )
			);
		}
		return entreprise;
	}
	
	public static Entreprise find_by_name(String nom_utilisateur) throws SQLException {
		Entreprise entreprise = null;
		
		// Requête en BDD
		ResultSet resultat = query( "SELECT id_entreprise, id_utilisateur, nom_entreprise " +
				"FROM entreprise WHERE nom_entreprise ='"+ nom_utilisateur +"'");
		
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
		int ident = Integer.parseInt(id);
		ident = ident + 1;
		return ""+ident;
	}
	
	public static void addEntreprise(Entreprise entreprise) throws SQLException {
		update("INSERT into entreprise VALUES('"+entreprise.getId_entreprise()+"','"+entreprise.getId_utilisateur()+"','"+entreprise.getNom_entreprise()+"')");
	}
	
	public void update_by_params(String params_type, String params) throws SQLException {
		update("UPDATE entreprise SET "+ params_type +" = '"+ params +"' WHERE id_entreprise = '"+ this.getId_entreprise() +"'");
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
	public List<Produit> getProduits() {
		return produits;
	}
}

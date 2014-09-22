package Modeles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Produit extends Modele {

	// PB!!!! Pas l'identifiant de la partie par ex ou du tour!
	private String id_produit;
	private String id_entreprise;
	private String nom_produit;
	private String image_produit;
	private String stock;
	
	public Produit(String id_produit, String id_entreprise, String nom_produit,
			String image_produit, String stock) throws SQLException {
		super();
		this.id_produit = id_produit;
		this.id_entreprise = id_entreprise;
		this.nom_produit = nom_produit;
		this.image_produit = image_produit;
		this.stock = stock;
	}

	public static Produit find(String id_produit) throws SQLException {
		Produit produit = null;
		ResultSet resultat = query( "SELECT id_produit, id_entreprise, " +
				"nom_produit, image_produit, stock" +
				"FROM produit WHERE id_produit ='"+ id_produit +"'");
		
		if( resultat.next() ) {
			produit = new Produit(
					resultat.getString( "id_produit" ),
					resultat.getString( "id_entreprise" ), 
					resultat.getString( "nom_produit" ), 
					resultat.getString( "image_produit" ), 
					resultat.getString( "stock" )
			);
		}
		return produit;
	}
	
	public static List<Produit> find_by_entreprise(String id_entreprise) throws SQLException {
		List<Produit> produits = new ArrayList<Produit>();
		Produit produit = null;
		ResultSet resultat = query("SELECT id_produit, id_entreprise, nom_produit, image_produit, stock " +
				"FROM produit WHERE id_entreprise ='"+ id_entreprise +"'");
		while( resultat.next() ) {
			produit = new Produit(
					resultat.getString( "id_produit" ),
					resultat.getString( "id_entreprise" ), 
					resultat.getString( "nom_produit" ), 
					resultat.getString( "image_produit" ), 
					resultat.getString( "stock" )
			);
			produits.add(produit);
		}
		
		return produits;
	}
	
	
	public static String lastId() throws SQLException {
		String id = "";
		int ident = 0;
		ResultSet resultat = query( "SELECT max(id_produit) max_id FROM produit");
		
		while( resultat.next() ) {
			id = resultat.getString("max_id");
		}
		ident = Integer.parseInt(id);
		ident++;
		return ""+ident;
	}
	
	public static void addProduit(Produit pd) throws SQLException {
		update("INSERT into produit VALUES('"+pd.getId_produit()+"','"+pd.getId_entreprise()+"','"+pd.getNom_produit()+"','"+pd.getImage_produit()+"','"+pd.getStock()+"')");
	}

	public String getId_produit() {
		return id_produit;
	}

	public void setId_produit(String id_produit) {
		this.id_produit = id_produit;
	}

	public String getId_entreprise() {
		return id_entreprise;
	}

	public void setId_entreprise(String id_entreprise) {
		this.id_entreprise = id_entreprise;
	}

	public String getNom_produit() {
		return nom_produit;
	}

	public void setNom_produit(String nom_produit) {
		this.nom_produit = nom_produit;
	}

	public String getImage_produit() {
		return image_produit;
	}

	public void setImage_produit(String image_produit) {
		this.image_produit = image_produit;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

}

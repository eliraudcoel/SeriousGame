package Modeles;

public class Produit {

	private int id_produit;
	private String nom_produit;
	private String image_produit;
	private int stock;
	
	public Produit(int id_produit, String nom_produit, String image_produit,
			int stock) {
		super();
		this.id_produit = id_produit;
		this.nom_produit = nom_produit;
		this.image_produit = image_produit;
		this.stock = stock;
	}
	
	public int getId_produit() {
		return id_produit;
	}
	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
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
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}	
}

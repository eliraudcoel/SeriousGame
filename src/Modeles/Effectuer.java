package Modeles;

public class Effectuer {

	private String id_tour;
	private String id_utilisateur;
	
	public Effectuer(String id_tour, String id_utilisateur) {
		super();
		this.id_tour = id_tour;
		this.id_utilisateur = id_utilisateur;
	}
	
	public String getId_tour() {
		return id_tour;
	}
	public void setId_tour(String id_tour) {
		this.id_tour = id_tour;
	}
	public String getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(String id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
}

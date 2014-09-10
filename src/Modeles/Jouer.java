package Modeles;

import java.sql.SQLException;

public class Jouer extends Modele {
	
	private String id_utilisateur;
	private String id_tour;
	private String jouer;
	
	public Jouer(String id_utilisateur, String id_tour, String jouer) {
		super();
		this.id_utilisateur = id_utilisateur;
		this.id_tour = id_tour;
		this.jouer = jouer;
	}
	
	public static void addJeu(Jouer participation) throws SQLException {
		update("INSERT into participer VALUES('"+participation.getId_utilisateur()+"', '"+participation.getId_tour()+"', '"+participation.getJouer()+"')");
	}

	public String getId_utilisateur() {
		return id_utilisateur;
	}

	public void setId_utilisateur(String id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}

	public String getId_tour() {
		return id_tour;
	}

	public void setId_tour(String id_tour) {
		this.id_tour = id_tour;
	}

	public String getJouer() {
		return jouer;
	}

	public void setJouer(String jouer) {
		this.jouer = jouer;
	}
}
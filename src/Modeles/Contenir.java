package Modeles;

import java.sql.SQLException;

public class Contenir extends Modele {
	private String id_tour;
	private String id_evenement;
	private String nb_evenements;
	
	public Contenir(String id_tour, String id_evenement) {
		super();
		this.id_tour = id_tour;
		this.id_evenement = id_evenement;
		this.nb_evenements = "0";
	}
	
	public static void addContenir(Contenir contenir) throws SQLException {
		update("INSERT into contenir VALUES('"+contenir.getId_tour()+"', '"+contenir.getId_evenement()+"', "+contenir.getNb_evenements()+")");
	}
	
	public String getId_tour() {
		return id_tour;
	}
	public void setId_tour(String id_tour) {
		this.id_tour = id_tour;
	}
	public String getId_evenement() {
		return id_evenement;
	}
	public void setId_evenement(String id_evenement) {
		this.id_evenement = id_evenement;
	}
	public String getNb_evenements() {
		return nb_evenements;
	}
	public void setNb_evenements(String nb_evenements) {
		this.nb_evenements = nb_evenements;
	}
}

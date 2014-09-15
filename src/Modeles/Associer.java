package Modeles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Associer extends Modele {

	private String id_tour;
	private String id_entreprise;
	private String capital_actuel;
	
	public Associer(String id_tour, String id_entreprise, String capital_actuel) {
		super();
		this.id_tour = id_tour;
		this.id_entreprise = id_entreprise;
		this.capital_actuel = capital_actuel;
	}
	
	
	public static List<Associer> all() throws SQLException {
		List<Associer> results = new ArrayList<Associer>();
		
		ResultSet resultat = query( "SELECT id_tour, id_entreprise, capital_actuel FROM associer");
		while( resultat.next() ) {
			Associer associer = new Associer(
					resultat.getString( "id_tour" ), 
					resultat.getString( "id_entreprise" ), 
					resultat.getString( "capital_actuel" )
			);
			results.add(associer);
		}
		return results;
	}

	public static Associer find_by_entreprise(String id_entreprise, String id_tour) throws SQLException {
		Associer associer = null;
		
		ResultSet resultat = query( "SELECT id_tour, id_entreprise, capital_actuel FROM associer" +
				"WHERE id_entreprise="+ id_entreprise+"AND id_tour="+ id_tour);
		if( resultat.next() ) {
			associer = new Associer(
					resultat.getString( "id_tour" ), 
					resultat.getString( "id_entreprise" ), 
					resultat.getString( "capital_actuel" )
			);
		}
		return associer;
	}
	
	public String getId_tour() {
		return id_tour;
	}
	public void setId_tour(String id_tour) {
		this.id_tour = id_tour;
	}
	public String getId_entreprise() {
		return id_entreprise;
	}
	public void setId_entreprise(String id_entreprise) {
		this.id_entreprise = id_entreprise;
	}
	public String getCapital_actuel() {
		return capital_actuel;
	}
	public void setCapital_actuel(String capital_actuel) {
		this.capital_actuel = capital_actuel;
	}
}
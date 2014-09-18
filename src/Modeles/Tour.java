package Modeles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tour extends Modele {

	private String id_tour;
	private String id_partie;
	private String num_tour;
	private String regle;
	private String tour_actuel;
	
	public Tour(String id_tour, String id_partie, String num_tour,
			String regle, String tour_actuel) {
		super();
		this.id_tour = id_tour;
		this.id_partie = id_partie;
		this.num_tour = num_tour;
		this.regle = regle;
		this.tour_actuel = tour_actuel;
	}
	
	public static List<Tour> all() throws SQLException {
		List<Tour> results = new ArrayList<Tour>();
		
		ResultSet resultat = query( "SELECT id_tour, id_partie, num_tour, regle, tour_actuel FROM tour");
		while( resultat.next() ) {
			Tour tour = new Tour(
					resultat.getString( "id_tour" ),
					resultat.getString( "id_partie" ),
					resultat.getString( "num_tour" ),
					resultat.getString( "regle" ),
					resultat.getString( "tour_actuel" )
			);
			results.add(tour);
		}
		return results;
	}
	
	public static List<Tour> find_by_partie(String id_partie) throws SQLException {
		List<Tour> results = new ArrayList<Tour>();
		
		ResultSet resultat = query( "SELECT id_tour, id_partie, num_tour, regle, tour_actuel " +
				"FROM tour WHERE id_partie='"+ id_partie +"' ORDER BY num_tour ASC");
		while( resultat.next() ) {
			Tour tour = new Tour(
					resultat.getString( "id_tour" ),
					resultat.getString( "id_partie" ),
					resultat.getString( "num_tour" ),
					resultat.getString( "regle" ),
					resultat.getString( "tour_actuel" )
			);
			results.add(tour);
		}
		return results;
	}
	
	public static String lastId() throws SQLException {
		String id = "";
		int ident = 0;
		ResultSet resultat = query( "SELECT max(id_tour) max_id FROM tour");
		
		while( resultat.next() ) {
			id = resultat.getString("max_id");
		}
		ident = Integer.parseInt(id);
		ident++;
		return ""+ident;
	}
	
	public static Tour last_tour_of_partie(Partie partie) throws SQLException {
		Tour tour = null;
		
		ResultSet resultat = query( "SELECT id_tour, id_partie, num_tour, regle, tour_actuel " +
				"FROM tour WHERE id_partie ='"+ partie.getId_partie() +"'");
		while( resultat.next() ) {
			tour = new Tour(
					resultat.getString( "id_tour" ),
					resultat.getString( "id_partie" ),
					resultat.getString( "num_tour" ),
					resultat.getString( "regle" ),
					resultat.getString( "tour_actuel" )
			);
		}
		
		return tour;
	}
	
	public static void add_tour(Tour tour) throws SQLException {
		update("insert into tour (id_tour, id_partie, num_tour, regle, tour_actuel)" +
				"values('"+tour.getId_tour()+"','"+tour.getId_partie()+"','"+tour.getNum_tour()+
				"','"+tour.getRegle()+"','"+tour.getTour_actuel()+"')");
	}
	
	public String getId_tour() {
		return id_tour;
	}
	public void setId_tour(String id_tour) {
		this.id_tour = id_tour;
	}
	public String getId_partie() {
		return id_partie;
	}
	public void setId_partie(String id_partie) {
		this.id_partie = id_partie;
	}
	public String getNum_tour() {
		return num_tour;
	}
	public void setNum_tour(String num_tour) {
		this.num_tour = num_tour;
	}
	public String getRegle() {
		return regle;
	}
	public void setRegle(String regle) {
		this.regle = regle;
	}
	public String getTour_actuel() {
		return tour_actuel;
	}
	public void setTour_actuel(String tour_actuel) {
		this.tour_actuel = tour_actuel;
	}
}

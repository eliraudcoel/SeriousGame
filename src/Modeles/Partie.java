package Modeles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Partie extends Modele {
	
	private String id_partie;
	private String nom_partie;
	private String duree;
	private String capital_depart;
	private String cout_salaire;
	private String cout_charge_exp;
	private String cout_loyer;
	private String date_debut;
	private String date_fin;
	
	private List<Tour> tours;
	
	public Partie(String id_partie, String nom_partie, String duree,
			String capital_depart, String cout_salaire, String cout_charge_exp,
			String cout_loyer, String date_debut, String date_fin) throws SQLException {
		super();
		this.id_partie = id_partie;
		this.nom_partie = nom_partie;
		this.duree = duree;
		this.capital_depart = capital_depart;
		this.cout_salaire = cout_salaire;
		this.cout_charge_exp = cout_charge_exp;
		this.cout_loyer = cout_loyer;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.tours = Tour.find_by_partie(id_partie);
	}

	public static List<Partie> all() throws SQLException {
		List<Partie> results = new ArrayList<Partie>();
		
		ResultSet resultat = query( "SELECT id_partie, nom_partie, duree, " +
				"capital_depart, cout_salaire, cout_charge_exp, cout_loyer, " +
				"date_debut, date_fin FROM partie");
		while( resultat.next() ) {
			Partie partie = new Partie(
					resultat.getString( "id_partie" ),
					resultat.getString( "nom_partie" ), 
					resultat.getString( "duree" ), 
					resultat.getString( "capital_depart" ), 
					resultat.getString( "cout_salaire" ), 
					resultat.getString( "cout_charge_exp" ),
					resultat.getString( "cout_loyer" ),
					resultat.getString( "date_debut" ),
					resultat.getString( "date_fin" )
			);
			results.add(partie);
		}
		return results;
	}
	
	public static List<Partie> in_processing_partie() throws SQLException, ParseException {
		List<Partie> results = Partie.all();
		List<Partie> process_parties = new ArrayList<Partie>();
		
		for (Partie partie : results) {
			if(partie.is_processing() == true && partie.getDate_fin() == null) {
				process_parties.add(partie);
			}
		}
		
		return process_parties;
	}
	
	public static List<Partie> not_started_partie() throws SQLException, ParseException {
		List<Partie> results = Partie.all();
		List<Partie> not_started_parties = new ArrayList<Partie>();
		
		for (Partie partie : results) {
			if(partie.is_processing() == false && partie.getDate_fin() == null) {
				not_started_parties.add(partie);
			}
		}
		
		return not_started_parties;
	}
	
	public static List<Partie> finished_partie() throws SQLException, ParseException {
		List<Partie> results = Partie.all();
		List<Partie> finished_parties = new ArrayList<Partie>();
		
		for (Partie partie : results) {
			if(partie.getDate_fin() != null) {
				finished_parties.add(partie);
			}
		}
		
		return finished_parties;
	}
	
	public boolean is_processing() throws ParseException {
		// si la date est inférieur ==> en cours sinon non
		boolean en_cours = false;
		Date today = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date_debut = format.parse(this.getDate_debut());
		
		if(date_debut.compareTo(today) < 0 || date_debut.compareTo(today) == 0)
			en_cours = true;
		
		return en_cours;
	}
	
	public static Partie find(String id_partie) throws SQLException {
		Partie partie = null;
		ResultSet resultat = query( "SELECT id_partie, nom_partie, duree, " +
				"capital_depart, cout_salaire, cout_charge_exp, cout_loyer, " +
				"date_debut, date_fin FROM partie WHERE id_partie ='"+ id_partie +"'");
		
		if( resultat.next() ) {
			partie = new Partie(
					resultat.getString( "id_partie" ),
					resultat.getString( "nom_partie" ), 
					resultat.getString( "duree" ), 
					resultat.getString( "capital_depart" ), 
					resultat.getString( "cout_salaire" ), 
					resultat.getString( "cout_charge_exp" ),
					resultat.getString( "cout_loyer" ),
					resultat.getString( "date_debut" ),
					resultat.getString( "date_fin" )
			);
		}
		return partie;
	}
	
	public int nombre_joueurs() throws SQLException {
		ArrayList<Participation> participations = (ArrayList<Participation>) Participation.find_by_partie(this);
		return participations.size();
	}
	
	public void add_user(Utilisateur user) throws SQLException {
		Participation.addParticipant(user, this);
	}

	public String getId_partie() {
		return id_partie;
	}

	public void setId_partie(String id_partie) {
		this.id_partie = id_partie;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getCapital_depart() {
		return capital_depart;
	}

	public void setCapital_depart(String capital_depart) {
		this.capital_depart = capital_depart;
	}

	public String getCout_salaire() {
		return cout_salaire;
	}

	public void setCout_salaire(String cout_salaire) {
		this.cout_salaire = cout_salaire;
	}

	public String getCout_charge_exp() {
		return cout_charge_exp;
	}

	public void setCout_charge_exp(String cout_charge_exp) {
		this.cout_charge_exp = cout_charge_exp;
	}

	public String getCout_loyer() {
		return cout_loyer;
	}

	public void setCout_loyer(String cout_loyer) {
		this.cout_loyer = cout_loyer;
	}

	public String getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(String date_debut) {
		this.date_debut = date_debut;
	}

	public String getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}

	public String getNom_partie() {
		return nom_partie;
	}

	public void setNom_partie(String nom_partie) {
		this.nom_partie = nom_partie;
	}
	
	public List<Tour> getTours() {
		return tours;
	}
}

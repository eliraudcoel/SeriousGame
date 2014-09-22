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
	
	public Partie(String id_partie, String nom_partie, String duree,
			String capital_depart, String cout_salaire, String cout_charge_exp,
			String cout_loyer, String date_debut) throws SQLException {
		super();
		this.id_partie = id_partie;
		this.nom_partie = nom_partie;
		this.duree = duree;
		this.capital_depart = capital_depart;
		this.cout_salaire = cout_salaire;
		this.cout_charge_exp = cout_charge_exp;
		this.cout_loyer = cout_loyer;
		this.date_debut = date_debut;
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
	
	public static List<Partie> in_processing_partie(List<Partie> results) throws SQLException, ParseException {
		List<Partie> process_parties = new ArrayList<Partie>();
		
		for (Partie partie : results) {
			if(partie.is_processing() && partie.getDate_fin() == null) {
				process_parties.add(partie);
			}
		}
		
		return process_parties;
	}
	
	public static List<Partie> not_started_partie(List<Partie> results) throws SQLException, ParseException {
		List<Partie> not_started_parties = new ArrayList<Partie>();
		
		for (Partie partie : results) {
			if(!partie.is_processing() && partie.getDate_fin() == null) {
				not_started_parties.add(partie);
			}
		}
		
		return not_started_parties;
	}
	
	public static List<Partie> finished_partie(List<Partie> results) throws SQLException, ParseException {
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
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date_debut = format.parse(this.getDate_debut());
		
		if(today.compareTo(date_debut) == 0)
			if(today.getHours() > date_debut.getHours())
				en_cours = true;
		if(today.compareTo(date_debut) == 1)
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
	
	public ArrayList<Participation> get_all_participations() throws SQLException {
		return (ArrayList<Participation>) Participation.find_by_partie(this);
	}
	
	public ArrayList<Participation> get_all_participations_only_users() throws SQLException {
		return (ArrayList<Participation>) Participation.find_by_partie_only_users(this);
	}
	
	public List<Utilisateur> get_all_utilisateurs() throws SQLException {
		ArrayList<Participation> participations = get_all_participations();
		List<Utilisateur> users = new ArrayList<Utilisateur>();
		Utilisateur user = null;
		
		for (Participation participation : participations) {
			user = Utilisateur.find(participation.getId_utilisateur());
			if(!user.is_admin())
				users.add(user);
		}
		return users;
	}
	
	public int nombre_joueurs() throws SQLException {
		ArrayList<Participation> participations = get_all_participations();
		return participations.size();
	}

	public static String lastId() throws SQLException {
		String id = "";
		int ident = 0;
		ResultSet resultat = query( "SELECT max(id_partie) max_id FROM partie");
		
		while( resultat.next() ) {
			id = resultat.getString("max_id");
		}
		ident = Integer.parseInt(id);
		ident++;
		return ""+ident;
	}

	public void add_partie() throws SQLException {
		update("insert into partie " +
				"(id_partie, nom_partie, duree, capital_depart, cout_salaire, cout_charge_exp, cout_loyer, date_debut)"+
				"values('"+this.getId_partie()+"','"+this.getNom_partie()+"','"+this.getDuree()+
				"','"+this.getCapital_depart()+"','"+this.getCout_salaire()+"','"+this.getCout_charge_exp()+
				"','"+this.getCout_loyer()+"',TO_DATE('"+this.getDate_debut()+"', 'dd/mm/yyyy hh24:mi') )");
		
		Tour tour = new Tour(Tour.lastId(), this.getId_partie(), "1", "", "1");
		Tour.add_tour(tour);
	}
	
	public boolean is_participe(Utilisateur user) throws SQLException {
		boolean has_participe = false;
		ResultSet resultat = query( "SELECT id_utilisateur, id_partie FROM jouer WHERE id_partie='"+ this.getId_partie()+"' AND id_utilisateur='"+ user.getId()+"'");
		
		if(resultat.next()) {
			has_participe = true;
		}
		return has_participe;
	}
	
	public boolean has_admin() throws SQLException {
		boolean admin = false;
		ArrayList<Participation> participations = get_all_participations();
		
		for (Participation participation : participations) {
			Utilisateur user = Utilisateur.find(participation.getId_utilisateur());
			if(user.is_admin())
				admin = true;
		}
		return admin;
	}
	
	public boolean admin_can_play() throws SQLException {
		boolean can = false;
		ArrayList<Participation> participations = get_all_participations_only_users();
		Tour last_tour = Tour.last_tour_of_partie(this);
		int count = 0;
		
		for (Participation participation : participations) {
			Utilisateur user = Utilisateur.find(participation.getId_utilisateur());
			Entreprise ent = user.getEntreprise();
			Associer associer = Associer.find_by_entreprise(ent.getId_entreprise(), last_tour.getId_tour());
			if(associer != null)
				count = count + 1;
		}
		
		if(count == participations.size()) {
			can = true;
		}
		
		return can;
	}
	
	public boolean user_can_play(Utilisateur user) throws SQLException {
		boolean can = true;
		Tour last_tour = Tour.last_tour_of_partie(this);
		Entreprise ent = user.getEntreprise();
		Associer associer = Associer.find_by_entreprise(ent.getId_entreprise(), last_tour.getId_tour());
		if(associer != null) {
			can = false;
		}
		
		return can;
	}
	
	public void update_by_params(String params_type, String params) throws SQLException {
		update("UPDATE partie SET "+ params_type +" = '"+ params +"' WHERE id_partie = '"+ this.getId_partie() +"'");
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

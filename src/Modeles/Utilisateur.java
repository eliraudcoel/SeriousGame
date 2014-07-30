package Modeles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur extends Modele {

	private String id;
	private String login;
	private String mdp;
	private String email;
	private String admin;
	
	public Utilisateur(String id, String login, String mdp, String email,
			String admin) {
		super();
		this.id = id;
		this.login = login;
		this.mdp = mdp;
		this.email = email;
		this.admin = admin;
	}
	
	public static Utilisateur find_by_identification(String login, String mdp) throws SQLException {
		Utilisateur user = null;
		
		// Requête en BDD
		ResultSet resultat = query( "SELECT id_utilisateur, login, mdp, email, admin FROM utilisateur WHERE login ='"+ login +"' AND mdp ='"+ mdp +"'");
		
		// Tant qu'il y a des resultats on créé un object Pays
		if( resultat.next() ) {
			user = new Utilisateur(
					resultat.getString( "id_utilisateur" ), 
					resultat.getString( "login" ), 
					resultat.getString( "mdp" ), 
					resultat.getString( "email" ), 
					resultat.getString( "admin" )
			);
		}
		return user;
	}
	
	public static Utilisateur find_by_email(String email) throws SQLException {
		Utilisateur user = null;
		
		// Requête en BDD
		ResultSet resultat = query( "SELECT id_utilisateur, login, mdp, email, admin FROM utilisateur WHERE email ='"+ email +"'");
		
		// Tant qu'il y a des resultats on créé un object Pays
		if( resultat.next() ) {
			user = new Utilisateur(
					resultat.getString( "id_utilisateur" ), 
					resultat.getString( "login" ), 
					resultat.getString( "mdp" ), 
					resultat.getString( "email" ), 
					resultat.getString( "admin" )
			);
		}
		return user;
	}
	
	public static Utilisateur find(String id_utilisateur) throws SQLException {
		Utilisateur user = null;
		
		// Requête en BDD
		ResultSet resultat = query( "SELECT id_utilisateur, login, mdp, email, admin FROM utilisateur WHERE id_utilisateur ='"+ id_utilisateur +"'");
		
		// Tant qu'il y a des resultats on créé un object Pays
		if( resultat.next() ) {
			user = new Utilisateur(
					resultat.getString( "id_utilisateur" ), 
					resultat.getString( "login" ), 
					resultat.getString( "mdp" ), 
					resultat.getString( "email" ), 
					resultat.getString( "admin" )
			);
		}
		return user;
	}

	public static List<Utilisateur> all() throws SQLException {
		List<Utilisateur> results = new ArrayList<Utilisateur>();
		
		ResultSet resultat = query( "SELECT id_utilisateur, login, mdp, email, admin FROM utilisateur");
		while( resultat.next() ) {
			Utilisateur user = new Utilisateur(
					resultat.getString( "id_utilisateur" ), 
					resultat.getString( "login" ), 
					resultat.getString( "mdp" ), 
					resultat.getString( "email" ), 
					resultat.getString( "admin" )
			);
			results.add(user);
		}
		return results;
	}
	
	public static String lastId() throws SQLException {
		String id = "";
		List<Utilisateur> users = Utilisateur.all();
		
		for (Utilisateur user : users) {
			id = user.getId();
		}
		int ident = Integer.parseInt(id);
		ident = ident + 1;
		return ""+ident;
	}
	
	public static void addUser(Utilisateur user) throws SQLException {
		update("INSERT into utilisateur VALUES('"+user.getId()+"','"+user.getLogin()+"','"+user.getMdp()+"','"+user.getEmail()+"','"+user.getAdmin()+"')");
	}
	
	public static void updateUser(Utilisateur user) throws SQLException {
		update("UPDATE utilisateur SET login = '"+user.getLogin()+"', mdp = '"+user.getMdp()+"', email = '"+user.getEmail()+"' WHERE id_utilisateur = '"+ user.getId() +"'");
	}
	
	public void update_by_params(String params_type, String params) throws SQLException {
		update("UPDATE utilisateur SET "+ params_type +" = '"+ params +"' WHERE id_utilisateur = '"+ this.getId() +"'");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
}

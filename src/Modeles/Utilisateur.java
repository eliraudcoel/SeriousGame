package Modeles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur extends Modele {

	private String id;
	private String id_entreprise;
	private String login;
	private String mdp;
	private String admin;
	
	private Entreprise entreprise;
	
	public Utilisateur(String id, String id_entreprise, String login,
			String mdp, String admin) throws SQLException {
		super();
		this.id = id;
		this.id_entreprise = id_entreprise;
		this.login = login;
		this.mdp = mdp;
		this.admin = admin;
		if(admin.equals("0")) {
			this.entreprise = Entreprise.find(id_entreprise);
		}
	}

	public Utilisateur(String id, String login, String mdp, String admin) {
		super();
		this.id = id;
		this.login = login;
		this.mdp = mdp;
		this.admin = admin;
	}

	public static Utilisateur find_by_identification(String login, String mdp) throws SQLException {
		Utilisateur user = null;
		
		ResultSet resultat = query( "SELECT id_utilisateur, id_entreprise, login, mdp, admin FROM utilisateur WHERE login ='"+ login +"' AND mdp ='"+ mdp +"'");
		
		if( resultat.next() ) {
			if(resultat.getString( "id_entreprise" ) != null) {
				user = new Utilisateur(
						resultat.getString( "id_utilisateur" ),
						resultat.getString( "id_entreprise" ),
						resultat.getString( "login" ), 
						resultat.getString( "mdp" ),
						resultat.getString( "admin" )
				);
			} else {
				user = new Utilisateur(
						resultat.getString( "id_utilisateur" ),
						resultat.getString( "login" ), 
						resultat.getString( "mdp" ),
						resultat.getString( "admin" )
				);
			}
		}
		return user;
	}
	
	public static Utilisateur find_by_email(String email) throws SQLException {
		Utilisateur user = null;
		
		ResultSet resultat = query( "SELECT id_utilisateur, id_entreprise, login, mdp, admin FROM utilisateur WHERE email ='"+ email +"'");
		
		if( resultat.next() ) {
			user = new Utilisateur(
					resultat.getString( "id_utilisateur" ),
					resultat.getString( "id_entreprise" ),
					resultat.getString( "login" ), 
					resultat.getString( "mdp" ), 
					resultat.getString( "admin" )
			);
		}
		return user;
	}
	
	public static Utilisateur find(String id_utilisateur) throws SQLException {
		Utilisateur user = null;
		
		ResultSet resultat = query( "SELECT id_utilisateur, id_entreprise, login, mdp, admin FROM utilisateur WHERE id_utilisateur ='"+ id_utilisateur +"'");
		
		if( resultat.next() ) {
			user = new Utilisateur(
					resultat.getString( "id_utilisateur" ),
					resultat.getString( "id_entreprise" ),
					resultat.getString( "login" ), 
					resultat.getString( "mdp" ),
					resultat.getString( "admin" )
			);
		}
		return user;
	}

	public static List<Utilisateur> all() throws SQLException {
		List<Utilisateur> results = new ArrayList<Utilisateur>();
		
		ResultSet resultat = query( "SELECT id_utilisateur, id_entreprise, login, mdp, admin FROM utilisateur");
		while( resultat.next() ) {
			Utilisateur user = new Utilisateur(
					resultat.getString( "id_utilisateur" ),
					resultat.getString( "id_entreprise" ),
					resultat.getString( "login" ), 
					resultat.getString( "mdp" ),
					resultat.getString( "admin" )
			);
			results.add(user);
		}
		return results;
	}
	
	public static String lastId() throws SQLException {
		String id = "";
		int ident = 0;
		ResultSet resultat = query( "SELECT max(id_utilisateur) max_id FROM utilisateur");
		
		while( resultat.next() ) {
			id = resultat.getString("max_id");
		}
		ident = Integer.parseInt(id);
		ident++;
		return ""+ident;
	}
	
	public static void addUser(Utilisateur user) throws SQLException {
		update("INSERT into utilisateur VALUES('"+user.getId()+"','"+user.getId_entreprise()+"','"+user.getLogin()+"','"+user.getMdp()+"','"+user.getAdmin()+"')");
	}
	
	public static void addUserWithoutEntreprise(Utilisateur user) throws SQLException {
		update("INSERT into utilisateur (id_utilisateur, login, mdp, admin) VALUES('"+user.getId()+"','"+user.getLogin()+"','"+user.getMdp()+"','"+user.getAdmin()+"')");
	}
	
	public static void updateUser(Utilisateur user) throws SQLException {
		update("UPDATE utilisateur SET login = '"+user.getLogin()+"', mdp = '"+user.getMdp()+"' WHERE id_utilisateur = '"+ user.getId() +"'");
	}
	
	public void update_by_params(String params_type, String params) throws SQLException {
		update("UPDATE utilisateur SET "+ params_type +" = '"+ params +"' WHERE id_utilisateur = '"+ this.getId() +"'");
	}
	
	public boolean is_admin() {
		if(this.getAdmin().equals("1"))
			return true;
		else
			return false;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_entreprise() {
		return id_entreprise;
	}
	public void setId_entreprise(String id_entreprise) {
		this.id_entreprise = id_entreprise;
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
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public void setEntreprise() throws SQLException {
		this.entreprise = Entreprise.find(id_entreprise);
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
}

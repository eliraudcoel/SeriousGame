package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe Database qui va s'occuper QUE de la connection avec la Base de donn�es (BDD)
public class Database {
	
	// On met en static les donn�es pour se connecter � la BDD - ils ne sont pas cens�s changer et etre changer
	public static String driver = "com.mysql.jdbc.Driver";
	public static String url = "jdbc:mysql://127.0.0.1/playbusiness";
	public static String utilisateur = "root";
	public static String motDePasse = "";
	
	// M�thode connexion() qui permet de se connecter � la BDD
	public static Connection connexion() throws SQLException, ClassNotFoundException {
		Connection connexion = null;
		Class.forName(driver);
		connexion = DriverManager.getConnection(url, utilisateur,motDePasse);
		
		return connexion;
	}
}

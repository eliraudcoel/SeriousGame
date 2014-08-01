package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe Database qui va s'occuper QUE de la connection avec la Base de données (BDD)
public class Database {
	
	// On met en static les données pour se connecter à la BDD - ils ne sont pas censés changer et etre changer
	public static String driver = "com.mysql.jdbc.Driver";
	public static String url = "jdbc:mysql://127.0.0.1/playbusiness";
	public static String utilisateur = "root";
	public static String motDePasse = "";
	
	// Méthode connexion() qui permet de se connecter à la BDD
	public static Connection connexion() throws SQLException, ClassNotFoundException {
		Connection connexion = null;
		Class.forName(driver);
		connexion = DriverManager.getConnection(url, utilisateur,motDePasse);
		
		return connexion;
	}
}

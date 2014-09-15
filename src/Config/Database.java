package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe Database qui va s'occuper QUE de la connection avec la Base de données (BDD)
public class Database {	
	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:thin:@bs-it.fr:1521:xe";
	public static String utilisateur = "serious_admin";
	public static String motDePasse = "serious_admin";
	
	// Méthode connexion() qui permet de se connecter à la BDD
	public static Connection connexion() throws SQLException, ClassNotFoundException {
		Connection connexion = null;
		Class.forName(driver);
		connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
		
		return connexion;
	}
}

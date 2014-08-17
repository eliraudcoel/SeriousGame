package Modeles;

import Config.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

// Classe h�rit� par Pays, Groupe et Match
public class Modele {
	
	// On cr�er une m�thode getConnection() qui permet de se connecter � la BDD
	public static Connection getConnection() {
		try {
			return Database.connexion();
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Impossible de se connecter � la Base de Donn�e", e);
		}
	}
	
	// m�thode query() qui permet d'effectuer des requ�tes en base
	public static ResultSet query(String sqlQuery) throws SQLException {
		Statement statement = (Statement) getConnection().createStatement();
		ResultSet resultat = statement.executeQuery(sqlQuery);
		return resultat;
	}
	
	public static void update(String sqlQuery) throws SQLException {
		Statement statement = (Statement) getConnection().createStatement();
		statement.executeUpdate(sqlQuery);
	}
}

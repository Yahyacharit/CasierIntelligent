package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInteraction {

	private static String url = "jdbc:mysql://localhost:3301/systemgestion";
	private static Connection conn;
	private static Statement stmt;
	public static void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root","");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.err.println("problem de pilote" + e);
		} catch (SQLException e) {
			System.err.println("problem de connection" + e);
		}
	}
	
	public static void disconnect() {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("problem de deconnection" + e);
		}
	}
	
	public static int maj(String rqt) {
		int nb=0;
		try {
			nb = stmt.executeUpdate(rqt);
		} catch (SQLException e) {
			System.err.println("problem de Update" + e);
		}
		return nb;
	}

	public static ResultSet selection(String rqt) {
		ResultSet res = null;
		try {
			res = stmt.executeQuery(rqt);
		} catch (SQLException e) {
			System.err.println("problem de Selection" + e);
		}
		return res;
	}
	public static void main(String[] args) {
		DBInteraction.connect();
	}
}

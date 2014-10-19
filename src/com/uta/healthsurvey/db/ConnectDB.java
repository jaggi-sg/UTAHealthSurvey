package com.uta.healthsurvey.db;

import java.sql.*;

public class ConnectDB {
	static Connection con;
	static String userName = "root";
	static String passWord = "";
	public static Connection db() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/survey", userName, passWord);
			System.out.println("Connected...." + con);
			return con;
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*Access DB Here*/

	public static void main(String[] args) {
		new ConnectDB();
	}
}

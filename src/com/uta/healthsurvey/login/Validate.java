package com.uta.healthsurvey.login;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Validate {

	public static boolean checkUser(String login, String password)
	{
		boolean st=false;
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/survey","root","Jaggi@830");
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("select * from health_officials where official_login=? and official_password=?");
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet result=statement.executeQuery();
			st=result.next();
			
				/*request.setAttribute("fname",result.getString("Firstname"));
					response.getWriter().println("<a href=\"index.html\">Click here to go Back</a>");
				else
					response.getWriter().println ("<html><body><script>alert('Hello World!');</script></body></html>");*/
			
			//statement = (PreparedStatement) con.prepareStatement(sql);
			//statement.setString(1, id);
		
			statement.close();
			//response.getWriter().println("<a href=\"index.html\">Click here to go Back</a>");
			//PreparedStatement statement0 = (PreparedStatement) con.prepareStatement("select n.nur_id from measurements m, nurses n where m.nur_id=n.nur_id");
						
			
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("The error is " + e.getMessage());
			e.printStackTrace();
		}
		return st;
	}
}

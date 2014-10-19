package com.uta.healthsurvey.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class fetchEnvData
 */
@WebServlet("/fetchEnvData")
public class FetchEnvData extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String state = request.getParameter("hi_state");
		String city = request.getParameter("hi_city");

		Connection con=null;
		response.setContentType("text/html");
		response.getWriter().println("<html>");
		response.getWriter().println("<head>");
		response.getWriter().println("<style>.container{"

				+ "clear: both;"
				+ "background-color: #DEA;"
				+ "}</style>");
		response.getWriter().println("<title> Location Based Survey Data </title>");
		response.getWriter().println("</head>");
		response.getWriter().println("<body><div class='container'>");
		response.getWriter().println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		response.getWriter().println("<img src='http://www.ehs.ucsf.edu/sites/ehs.ucsf.edu/files/images/Public%20Health%20Logo%20sized.jpg' height='100' width='150''>");
		response.getWriter().println("<img src='https://www.uta.edu/engineering/_downloads/graphics/new_coe_signature.jpg' alt='logo' height='100' width='1024'>");
		response.getWriter().println("<img src='http://www.phac-aspc.gc.ca/about_apropos/evaluation/reports-rapports/2012-2013/sf-fs/assets/images/surveillance.jpg' height='100' width='100'>");
		response.getWriter().println("<h2>Location based information for Public Health Surveillance</h2>");


		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/survey","root","");

			PreparedStatement statement=(PreparedStatement) con.prepareStatement("select le.year, le.co2, le.pollen, le. accident_count from location_environment le, location l where l.zip_code=le.zip_code and l.city=? group by le.zip_code, year");
			//select year, zip_code, co2 from location_environment group by zip_code, year;

			statement.setString(1,city);
			response.getWriter().println("The following is the CO2, Pollen, Accident Count for the last 3 years in "+city+" City," +state+"<br/>");
			response.getWriter().println("<table border='2'>");
			ResultSet result=statement.executeQuery();
			response.getWriter().println("<tr><td>"+"Year"+"</td>" + "<td>"+"CO2 Level"+"</td>" + "<td>"+"Pollen Concentration"+"</td>" + "<td>"+"Accident Count"+"</td>");
			//Select survey_year, Sum( Case When smoking = 'Y' Then 1 Else 0 End )/ Sum( Case When smoking <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey where state='Texas' Group By survey_year;
			//select count(*), state from survey where survey_year=? and state=? and chronic_disease=2102 and Smoking='Y' group by state

			while(result.next()){

				response.getWriter().println("<tr>");
				//response.getWriter().println("<tr><td>" + "Count" + "</td></tr>");
				response.getWriter().println("<td>" + result.getString(1) + "</td>");
				response.getWriter().println("<td>" + result.getString(2) + "</td>");
				response.getWriter().println("<td>" + result.getString(3) + "</td>");
				response.getWriter().println("<td>" + result.getString(4) + "</td>");
				response.getWriter().println("</tr>");

			}
			response.getWriter().println("</table><br/>");
			response.getWriter().println("<a href=\"healthindicator.html\">Click here to go Back</a><br/>");
			response.getWriter().println("<a href=\"login.html\">Logout</a><br/>");
			response.getWriter().println("<a href=\"index.html\">Home</a><br/>");
			response.getWriter().println("</div></body></html>");

		}
		catch(Exception e){
			System.out.println("The error is " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

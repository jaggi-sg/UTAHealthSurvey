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
 * Servlet implementation class fetchTimeData
 */
@WebServlet("/fetchTimeData")
public class FetchTimeData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String year = request.getParameter("hi_year");
		String state = request.getParameter("hi_state");
		String indicator = request.getParameter("hi_indicator");
		String city = request.getParameter("hi_city");

		Connection con=null;
		response.setContentType("text/html");
		response.getWriter().println("<html>");
		response.getWriter().println("<head>");
		response.getWriter().println("<title> Time Based Survey Data </title>");
		response.getWriter().println("</head>");
		response.getWriter().println("<style>.container{"
				
				+ "clear: both;"
				+ "background-color: #DEA;"
				+ "}</style>");
		response.getWriter().println("<body><div class='container'>");
		response.getWriter().println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		response.getWriter().println("<img src='http://www.ehs.ucsf.edu/sites/ehs.ucsf.edu/files/images/Public%20Health%20Logo%20sized.jpg' height='100' width='150''>");
		response.getWriter().println("<img src='https://www.uta.edu/engineering/_downloads/graphics/new_coe_signature.jpg' alt='logo' height='100' width='1024'>");
		response.getWriter().println("<img src='http://www.phac-aspc.gc.ca/about_apropos/evaluation/reports-rapports/2012-2013/sf-fs/assets/images/surveillance.jpg' height='100' width='100'>");
		response.getWriter().println("<h2>Time based information for Public Health Surveillance</h2>");

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/survey","root","");

//For state Texas
			if(state.equals("Texas")){
//Smoking for last3/5 years----Texas
				if((year.equals("last3")||year.equals("last5"))&&indicator.equals("Smoking"))
				{
					PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select survey_year, Sum( Case When smoking = 'Y' Then 1 Else 0 End )/ Sum( Case When smoking <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey where city=? and survey_year between ? and ? Group By survey_year");
					if(year.equals("last3"))
						statement.setString(2,"2012");
					else
						statement.setString(2,"2010");
					statement.setString(1,city);
					statement.setString(3,"2014");
					response.getWriter().println("The following is the "+indicator+" trend, for "+year+" years in "+city+", "+state+"<br/>");
					response.getWriter().println("<table border='1'>");
					ResultSet result=statement.executeQuery();
					response.getWriter().println("<tr><td>"+"YEAR"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+city+"</td>");
					//Select survey_year, Sum( Case When smoking = 'Y' Then 1 Else 0 End )/ Sum( Case When smoking <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey where state='Texas' Group By survey_year;
					//select count(*), state from survey where survey_year=? and state=? and chronic_disease=2102 and Smoking='Y' group by state
					
					while(result.next()){

						response.getWriter().println("<tr>");
						//response.getWriter().println("<tr><td>" + "Count" + "</td></tr>");
						response.getWriter().println("<td>" + result.getString(1) + "</td>");
						response.getWriter().println("<td>" + result.getString(2) + "</td>");
						response.getWriter().println("</tr>");

					}
					
					
				}

//Cancer for last 3/5 years ----Texas
				else if((year.equals("last3")||year.equals("last5"))&&indicator.equals("Cancer")){
					PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select s.survey_year, Sum(Case When s.disease_code = 2103 Then 1 Else 0 End )/Sum( Case When s.disease_code <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey s, chronic_diseases cd where s.city=? and s.disease_code=cd.disease_code and s.survey_year between ? and ? Group By s.survey_year");
					if(year.equals("last3"))
						statement.setString(2,"2012");
					else
						statement.setString(2,"2010");
					statement.setString(1,city);
					statement.setString(3,"2014");
					response.getWriter().println("The following is the "+indicator+" trend, for "+year+" years in "+city+", "+state+"<br/>");
					response.getWriter().println("<table border='1'>");
					ResultSet result=statement.executeQuery();	
					response.getWriter().println("<tr><td>"+"YEAR"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+city+"</td>");
					//Select survey_year, Sum( Case When smoking = 'Y' Then 1 Else 0 End )/ Sum( Case When smoking <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey where state='Texas' Group By survey_year;
					//select count(*), state from survey where survey_year=? and state=? and chronic_disease=2102 and Smoking='Y' group by state

					while(result.next()){

						response.getWriter().println("<tr>");
						//response.getWriter().println("<tr><td>" + "Count" + "</td></tr>");
						response.getWriter().println("<td>" + result.getString(1) + "</td>");
						response.getWriter().println("<td>" + result.getString(2) + "</td>");
						response.getWriter().println("</tr>");
					}
				}

//Diabetes for last 3/5 years ----Texas
				else if((year.equals("last3")||year.equals("last5"))&&indicator.equals("Diabetes")){
					PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select s.survey_year, Sum(Case When s.disease_code = 2103 Then 1 Else 0 End )/Sum( Case When s.disease_code <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey s, chronic_diseases cd where s.city=? and s.disease_code=cd.disease_code and s.survey_year between ? and ? Group By s.survey_year");
					if(year.equals("last3"))
						statement.setString(2,"2012");
					else
						statement.setString(2,"2010");
					statement.setString(1,city);
					statement.setString(3,"2014");
					response.getWriter().println("The following is the "+indicator+" trend, for "+year+" years in "+city+", "+state+"<br/>");
					response.getWriter().println("<table border='1'>");
					ResultSet result=statement.executeQuery();
					response.getWriter().println("<tr><td>"+"YEAR"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+city+"</td>");
					//Select survey_year, Sum( Case When smoking = 'Y' Then 1 Else 0 End )/ Sum( Case When smoking <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey where state='Texas' Group By survey_year;
					//select count(*), state from survey where survey_year=? and state=? and chronic_disease=2102 and Smoking='Y' group by state

					while(result.next()){

						response.getWriter().println("<tr>");
						//response.getWriter().println("<tr><td>" + "Count" + "</td></tr>");
						response.getWriter().println("<td>" + result.getString(1) + "</td>");
						response.getWriter().println("<td>" + result.getString(2) + "</td>");
						response.getWriter().println("</tr>");
					}
				}
				
				else if((year.equals("last3")||year.equals("last5"))&&indicator.equals("Arthritis")){
					PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select s.survey_year, Sum(Case When s.disease_code = 2105 Then 1 Else 0 End )/Sum( Case When s.disease_code <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey s, chronic_diseases cd where s.city=? and s.disease_code=cd.disease_code and s.survey_year between ? and ? Group By s.survey_year");
					if(year.equals("last3"))
						statement.setString(2,"2012");
					else
						statement.setString(2,"2010");
					statement.setString(1,city);
					statement.setString(3,"2014");
					response.getWriter().println("The following is the "+indicator+" trend, for "+year+" years in "+city+", "+state+"<br/>");
					response.getWriter().println("<table border='1'>");
					ResultSet result=statement.executeQuery();
					response.getWriter().println("<tr><td>"+"YEAR"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+city+"</td>");
					//Select survey_year, Sum( Case When smoking = 'Y' Then 1 Else 0 End )/ Sum( Case When smoking <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey where state='Texas' Group By survey_year;
					//select count(*), state from survey where survey_year=? and state=? and chronic_disease=2102 and Smoking='Y' group by state

					while(result.next()){

						response.getWriter().println("<tr>");
						//response.getWriter().println("<tr><td>" + "Count" + "</td></tr>");
						response.getWriter().println("<td>" + result.getString(1) + "</td>");
						response.getWriter().println("<td>" + result.getString(2) + "</td>");
						response.getWriter().println("</tr>");
					}
				}
				
				else if((year.equals("last3")||year.equals("last5"))&&indicator.equals("Obesity")){
					PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select s.survey_year, Sum(Case When s.bmi > 30 and s.state=? Then 1 Else 0 End )/Sum( Case When s.bmi > 30 Then 1 Else 0 End ) * 1.0 As Ratio From survey s where s.city=? and s.survey_year between ? and ? Group By s.survey_year");
					if(year.equals("last3"))
						statement.setString(3,"2012");
					else
						statement.setString(3,"2010");
					statement.setString(1,state);
					statement.setString(2,city);
					statement.setString(4,"2014");
					response.getWriter().println("The following is the "+indicator+" trend, for "+year+" years in "+city+", "+state+"<br/>");
					response.getWriter().println("<table border='1'>");
					ResultSet result=statement.executeQuery();
					response.getWriter().println("<tr><td>"+"YEAR"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+city+"</td>");
					//Select survey_year, Sum( Case When smoking = 'Y' Then 1 Else 0 End )/ Sum( Case When smoking <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey where state='Texas' Group By survey_year;
					//select count(*), state from survey where survey_year=? and state=? and chronic_disease=2102 and Smoking='Y' group by state

					while(result.next()){

						response.getWriter().println("<tr>");
						//response.getWriter().println("<tr><td>" + "Count" + "</td></tr>");
						response.getWriter().println("<td>" + result.getString(1) + "</td>");
						response.getWriter().println("<td>" + result.getString(2) + "</td>");
						response.getWriter().println("</tr>");
					}
				}

			}

//For state New York
			else if(state.equals("New York")){

			}
			
			response.getWriter().println("</table><br/>");
			PreparedStatement statement=(PreparedStatement) con.prepareStatement("select hl.hospital_name, hl.address, s.city, hl.specialization from survey s, hospital_location hl, location l where s.zip_code=hl.zip_code and l.zip_code=hl.zip_code and l.city=? group by s.city");
			//select year, zip_code, co2 from location_environment group by zip_code, year;

			statement.setString(1,city);
			response.getWriter().println("The following are the hospitals in & around the "+city+" City," +state+" State<br/>");
			response.getWriter().println("<table border='2'>");
			ResultSet result=statement.executeQuery();
			response.getWriter().println("<tr><td>"+"Hospital Name"+"</td>" + "<td>"+"Address"+"</td>" + "<td>"+"City"+"</td>" + "<td>"+"Specialization"+"</td>");
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

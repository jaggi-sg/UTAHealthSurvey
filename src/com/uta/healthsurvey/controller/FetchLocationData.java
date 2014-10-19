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
 * Servlet implementation class fetchData
 */
@WebServlet("/fetchLocationData")
public class FetchLocationData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String year = request.getParameter("hi_year");
		String state = request.getParameter("hi_state");
		String indicator = request.getParameter("hi_indicator");

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
//Smoking and Alcohol for 2013 & 2014
			if((year.equals("2013")||year.equals("2014"))&&(indicator.equals("Smoking")||indicator.equals("Alcohol")))
			{
				if(indicator.equals("Smoking")){
					PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select city, Sum( Case When smoking = 'Y' Then 1 Else 0 End )/ Sum( Case When smoking <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey where state=? and survey_year=? Group By city");
					statement.setString(1,state);
					statement.setString(2,year);
					response.getWriter().println("The following is the "+indicator+" trend, for the year "+year+" in "+state+" state<br/>");
					response.getWriter().println("<table border='2'>");
					ResultSet result=statement.executeQuery();
					response.getWriter().println("<br/><tr><td>"+"CITY"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+state+"</td>");
					while(result.next()){

						response.getWriter().println("<tr>");
						//response.getWriter().println("<tr><td>" + "Count" + "</td></tr>");
						response.getWriter().println("<td>" + result.getString(1) + "</td>");
						response.getWriter().println("<td>" + result.getString(2) + "</td>");
						response.getWriter().println("</tr>");
					}
				}

				else if(indicator.equals("Alcohol")){
					PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select city, Sum( Case When alcohol = 'Y' Then 1 Else 0 End )/ Sum( Case When alcohol <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey where state=? and survey_year=? Group By city");

					statement.setString(1,state);
					statement.setString(2,year);
					response.getWriter().println("The following is the "+indicator+" trend, for the year "+year+" in "+state+" state<br/>");
					response.getWriter().println("<table border='2'>");
					ResultSet result=statement.executeQuery();
					response.getWriter().println("<br/><tr><td>"+"CITY"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+state+"</td>");
					while(result.next()){

						response.getWriter().println("<tr>");
						//response.getWriter().println("<tr><td>" + "Count" + "</td></tr>");
						response.getWriter().println("<td>" + result.getString(1) + "</td>");
						response.getWriter().println("<td>" + result.getString(2) + "</td>");
						response.getWriter().println("</tr>");

					}
				}

			}

//Smoking and alcohol for last 3 & 5 years -- Texas
			else if((year.equals("last3")||year.equals("last5"))&&(indicator.equals("Smoking")||indicator.equals("Alcohol"))){
				if((indicator.equals("Smoking"))){
					PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select city, Sum( Case When smoking = 'Y' Then 1 Else 0 End )/ Sum( Case When smoking <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey where state=? and survey_year between ? and ? Group By city");
					if(year.equals("last3"))
						statement.setString(2,"2012");
					else
						statement.setString(2,"2010");
					statement.setString(1,state);
					statement.setString(3,"2014");
					response.getWriter().println("The following is the "+indicator+" trend, for the "+year+" years in "+state+" state<br/>");
					response.getWriter().println("<table border='2'>");
					ResultSet result=statement.executeQuery();
					response.getWriter().println("<tr><td>"+"CITY"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+state+"</td>");
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
				else if((indicator.equals("Alcohol"))){
					PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select city, Sum( Case When alcohol = 'Y' Then 1 Else 0 End )/ Sum( Case When alcohol <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey where state=? and survey_year between ? and ? Group By city");
					if(year.equals("last3"))
						statement.setString(2,"2012");
					else
						statement.setString(2,"2010");
					statement.setString(1,state);
					statement.setString(3,"2014");
					response.getWriter().println("The following is the "+indicator+" trend, for the "+year+" years in "+state+" state<br/>");
					response.getWriter().println("<table border='2'>");
					ResultSet result=statement.executeQuery();
					response.getWriter().println("<tr><td>"+"CITY"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+state+"</td>");
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

//Diabetes for 2013 & 2014
			else if((year.equals("2013")||year.equals("2014"))&&(indicator.equals("Diabetes"))){
				PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select s.city, Sum(Case When s.disease_code = 2103 Then 1 Else 0 End )/Sum( Case When s.disease_code <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey s, chronic_diseases cd where state=? and s.disease_code=cd.disease_code and survey_year=? Group By city");

						//("Select city, Sum(Case When cdc.disease_code = 2103 Then 1 Else 0 End )/Sum( Case When cdc.disease_code <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey, cd_citizens cdc, chronic_diseases cd where state=? and cd.disease_code=cdc.disease_code and survey_year=? Group By city");

				//Select city, Sum( Case When chronic_disease = 2103 Then 1 Else 0 End )/Sum( Case When chronic_disease <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey, chronic_diseases where state='Texas' and chronic_disease=icd_code and survey_year=2014

				statement.setString(1,state);
				statement.setString(2,year);

				response.getWriter().println("The following is the "+indicator+" trend, for the "+year+" years in "+state+" state<br/>");
				response.getWriter().println("<table border='2'>");
				ResultSet result=statement.executeQuery();
				response.getWriter().println("<tr><td>"+"CITY"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+state+"</td>");
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

//Diabetes for last 3 & 5 years
			else if((year.equals("last3")||year.equals("last5"))&&(indicator.equals("Diabetes"))){

				PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select s.city, Sum(Case When s.disease_code = 2103 Then 1 Else 0 End )/Sum( Case When s.disease_code <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey s, chronic_diseases cd where state=? and s.disease_code=cd.disease_code and survey_year between ? and ? Group By city");

				//Select city, Sum( Case When chronic_disease = 2103 Then 1 Else 0 End )/Sum( Case When chronic_disease <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey, chronic_diseases where state='Texas' and chronic_disease=icd_code and survey_year=2014
				if(year.equals("last3"))
					statement.setString(2,"2012");
				else
					statement.setString(2,"2010");
				statement.setString(1,state);
				statement.setString(3,"2014");

				response.getWriter().println("The following is the "+indicator+" trend, for the "+year+" years in "+state+" state<br/>");
				response.getWriter().println("<table border='2'>");
				ResultSet result=statement.executeQuery();
				response.getWriter().println("<tr><td>"+"CITY"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+state+"</td>");
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

//Cancer for 2013 & 2014
			else if((year.equals("2013")||year.equals("2014"))&&(indicator.equals("Cancer"))){
				PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select s.city, Sum(Case When s.disease_code = 2102 Then 1 Else 0 End )/Sum( Case When s.disease_code <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey s, chronic_diseases cd where state=? and s.disease_code=cd.disease_code and survey_year=? Group By city");

						//("Select city, Sum(Case When cdc.disease_code = 2102 Then 1 Else 0 End )/Sum( Case When cdc.disease_code <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey, cd_citizens cdc, chronic_diseases cd where state=? and cd.disease_code=cdc.disease_code and survey_year=? Group By city");

				//Select city, Sum( Case When chronic_disease = 2103 Then 1 Else 0 End )/Sum( Case When chronic_disease <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey, chronic_diseases where state='Texas' and chronic_disease=icd_code and survey_year=2014

				statement.setString(1,state);
				statement.setString(2,year);

				response.getWriter().println("The following is the "+indicator+" trend, for the "+year+" years in "+state+" state<br/>");
				response.getWriter().println("<table border='2'>");
				ResultSet result=statement.executeQuery();
				response.getWriter().println("<tr><td>"+"CITY"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+state+"</td>");
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

//Cancer for last 3 & 5 years --Texas
			else if((year.equals("last3")||year.equals("last5"))&&(indicator.equals("Cancer"))){

				PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select s.city, Sum(Case When s.disease_code = 2102 Then 1 Else 0 End )/Sum( Case When s.disease_code <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey s, chronic_diseases cd where s.state=? and s.disease_code=cd.disease_code and s.survey_year between ? and ? Group By s.city");

						//("Select city, Sum(Case When cdc.disease_code = 2102 Then 1 Else 0 End )/Sum( Case When cdc.disease_code <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey, cd_citizens cdc, chronic_diseases cd where state=? and cd.disease_code=cdc.disease_code and survey_year between ? and ? Group By city");
				//Select city, Sum( Case When chronic_disease = 2103 Then 1 Else 0 End )/Sum( Case When chronic_disease <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey, chronic_diseases where state='Texas' and chronic_disease=icd_code and survey_year=2014
				if(year.equals("last3"))
					statement.setString(2,"2012");
				else
					statement.setString(2,"2010");
				statement.setString(1,state);
				statement.setString(3,"2014");

				response.getWriter().println("The following is the "+indicator+" trend, for the "+year+" years in "+state+" state<br/>");
				response.getWriter().println("<table border='2'>");
				ResultSet result=statement.executeQuery();
				response.getWriter().println("<tr><td>"+"CITY"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+state+"</td>");
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
			
			
//Arthritis for 2013 & 2014
			else if((year.equals("2013")||year.equals("2014"))&&(indicator.equals("Arthritis"))){
				PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select s.city, Sum(Case When s.disease_code = 2105 Then 1 Else 0 End )/Sum( Case When s.disease_code <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey s, chronic_diseases cd where state=? and s.disease_code=cd.disease_code and survey_year=? Group By city");

						//("Select city, Sum(Case When cdc.disease_code = 2102 Then 1 Else 0 End )/Sum( Case When cdc.disease_code <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey, cd_citizens cdc, chronic_diseases cd where state=? and cd.disease_code=cdc.disease_code and survey_year=? Group By city");

				//Select city, Sum( Case When chronic_disease = 2103 Then 1 Else 0 End )/Sum( Case When chronic_disease <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey, chronic_diseases where state='Texas' and chronic_disease=icd_code and survey_year=2014

				statement.setString(1,state);
				statement.setString(2,year);

				response.getWriter().println("The following is the "+indicator+" trend, for the "+year+" years in "+state+" state<br/>");
				response.getWriter().println("<table border='2'>");
				ResultSet result=statement.executeQuery();
				response.getWriter().println("<tr><td>"+"CITY"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+state+"</td>");
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

//Arthritis for last 3 & 5 years --Texas
			else if((year.equals("last3")||year.equals("last5"))&&(indicator.equals("Arthritis"))){

				PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select s.city, Sum(Case When s.disease_code = 2105 Then 1 Else 0 End )/Sum( Case When s.disease_code <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey s, chronic_diseases cd where s.state=? and s.disease_code=cd.disease_code and s.survey_year between ? and ? Group By s.city");

						//("Select city, Sum(Case When cdc.disease_code = 2102 Then 1 Else 0 End )/Sum( Case When cdc.disease_code <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey, cd_citizens cdc, chronic_diseases cd where state=? and cd.disease_code=cdc.disease_code and survey_year between ? and ? Group By city");
				//Select city, Sum( Case When chronic_disease = 2103 Then 1 Else 0 End )/Sum( Case When chronic_disease <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey, chronic_diseases where state='Texas' and chronic_disease=icd_code and survey_year=2014
				if(year.equals("last3"))
					statement.setString(2,"2012");
				else
					statement.setString(2,"2010");
				statement.setString(1,state);
				statement.setString(3,"2014");

				response.getWriter().println("The following is the "+indicator+" trend, for the "+year+" years in "+state+" state<br/>");
				response.getWriter().println("<table border='2'>");
				ResultSet result=statement.executeQuery();
				response.getWriter().println("<tr><td>"+"CITY"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+state+"</td>");
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

//Obesity for last3/5 years
			else if((year.equals("last3")||year.equals("last5"))&&(indicator.equals("Obesity"))){

				PreparedStatement statement=(PreparedStatement) con.prepareStatement("Select city, Sum(Case When bmi>30 and state=? Then 1 Else 0 End )/Sum( Case When bmi <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey where state=? and survey_year between ? and ? Group By city order by city");

				//Select city, Sum( Case When chronic_disease = 2103 Then 1 Else 0 End )/Sum( Case When chronic_disease <> '' Then 1 Else 0 End ) * 1.0 As Ratio From survey, chronic_diseases where state='Texas' and chronic_disease=icd_code and survey_year=2014
				if(year.equals("last3"))
					statement.setString(3,"2012");
				else
					statement.setString(3,"2010");
				statement.setString(1,state);
				statement.setString(2,state);
				statement.setString(4,"2014");

				response.getWriter().println("The following is the "+indicator+" trend, for the "+year+" years in "+state+" state<br/>");
				response.getWriter().println("<table border='2'>");
				ResultSet result=statement.executeQuery();
				response.getWriter().println("<tr><td>"+"CITY"+"</td>" + "<td>"+"Ratio of "+indicator+" trend in "+state+"</td>");
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

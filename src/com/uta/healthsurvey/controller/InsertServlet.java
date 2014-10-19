package com.uta.healthsurvey.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    /**
	 * @see HttpServlet#doGet(HttpServletrequestuest requestuest, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		String fname = request.getParameter("survey_fname");
		String lname = request.getParameter("survey_lname");
		String address = request.getParameter("survey_address");
		String gender = request.getParameter("survey_gender");
		String dob = request.getParameter("survey_dob"); 
		String ssn = request.getParameter("survey_ssn");
		String phone = request.getParameter("survey_phone");
		String city = request.getParameter("survey_city");
		String state = request.getParameter("survey_state");
		String year = request.getParameter("survey_year");
		String zipcode = request.getParameter("survey_zipcode");
		String chronic_disease = request.getParameter("survey_chronic");
		String occupation = request.getParameter("survey_occupation");
		String height = request.getParameter("survey_height");
		String weight = request.getParameter("survey_weight");
		String smoking = request.getParameter("survey_smoking");
		String alcohol = request.getParameter("survey_alcohol");
		String survey_id = ssn+"_"+year;
		
		//String ip=request.getRemoteAddr();
		//response.getWriter().println("CONNECTED: 	" + name);
		Connection con = null;
		
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
		response.getWriter().println("<img src='http://www.ehs.ucsf.edu/sites/ehs.ucsf.edu/files/images/Public%20Health%20Logo%20sized.jpg' height='80' width='150''>");
		response.getWriter().println("<img src='https://www.uta.edu/engineering/_downloads/graphics/new_coe_signature.jpg' alt='logo' height='80' width='1024'>");
		response.getWriter().println("<img src='http://www.phac-aspc.gc.ca/about_apropos/evaluation/reports-rapports/2012-2013/sf-fs/assets/images/surveillance.jpg' height='80' width='100'>");


		//response.getWriter().println("ID is: " + id);
		//response.getWriter().println("<br/>Name is:" + name);
		//response.getWriter().println("<br/>Phone is:" + phone);
		//response.getWriter().println("</body>");
		//response.getWriter().println("</html>");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/survey","root","");
			//Statement stat = (Statement) con.createStatement();
			//stat.executeUpdate("insert into departments values (dept_id, dept_name, bed_capacity)");
			
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("insert into survey (first_name, last_name, ssn, address, city, state, zip_code, phone_number, date_of_birth, gender,"
					+ "occupation, survey_id, height, weight, survey_year, smoking, alcohol, disease_code) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			//statement = (PreparedStatement) con.prepareStatement(sql);
			statement.setString(1, fname);
			statement.setString(2, lname);
			statement.setString(3, ssn);
			statement.setString(4, address);
			statement.setString(5, city);
			statement.setString(6, state);
			statement.setString(7, zipcode);
			statement.setString(8, phone);
			statement.setString(9, dob);
			statement.setString(10, gender);
			statement.setString(11, occupation);
			statement.setString(12, survey_id);
			statement.setString(13, height);
			statement.setString(14, weight);
			statement.setString(15, year);
			statement.setString(16, smoking);
			statement.setString(17, alcohol);
			statement.setString(18, chronic_disease);
			
			
			
			
			int i=statement.executeUpdate();
			statement.close();
			if(i>0)
				response.getWriter().println("<div class='container'></br>Thanks for submitting the survey!!!<br/>");
			else
				response.getWriter().println("</br>Insert Unsuccessful</div>");
			
			response.getWriter().println("<a href=\"index.html\">Click here to go Back</a>");
			response.getWriter().println("<footer><p>Health Surveillance &#169; UTA 2014<br /> Contact information:<a href='mailto:someone@example.com'>email@email.com</a></p></footer>");
			response.getWriter().println("</div></body></html>");
			
			PreparedStatement statement1 = (PreparedStatement) con.prepareStatement("insert into cd_citizens (ssn, disease_code) values (?,?)");
			statement1.setString(1, ssn);
			statement1.setString(2, chronic_disease);
			statement1.executeUpdate();
			statement1.close();
		}
		
		
	
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("The error is " + e.getMessage());
			e.printStackTrace();
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("The error is " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletrequestuest requestuest, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

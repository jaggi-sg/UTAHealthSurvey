package com.uta.healthsurvey.login;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		// TODO Auto-generated method stub
		String login = request.getParameter("official_login");
		String password = request.getParameter("official_password");
		//Connection con = null;
		response.setContentType("text/html");

		if(Validate.checkUser(login,password))
		{
			RequestDispatcher rs=request.getRequestDispatcher("healthindicator.html");
			rs.include(request, response);
		}
		else
		{
			response.getWriter().println("<html><body onload=\"alert('Wrong Username/Password! Enter Again')\"></body></html>");
			//response.getWriter().println("<html><body><script>alert('Wrong Username/Password! Enter Again');</script></body></html>");
			RequestDispatcher rs=request.getRequestDispatcher("login.html");
			rs.include(request, response);

		}

	}

}

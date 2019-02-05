package com.myproject.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myproject.jdbc.daos.UsersDao;
import com.myproject.jdbc.pojos.Users;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName = request.getParameter("signUpFirstName");
		String lastName = request.getParameter("signUplastName");
		if (lastName != null)
			firstName += " " + lastName;
		String username = request.getParameter("signUpUsername");
		if (new UsersDao().checkAvailability(username)) {
			try {
				request.setAttribute("Notification", "<div class=\"col-md-12 alert-error-message\">"
						+ "  <span class=\"alert-closebtn\" onclick=\"this.parentElement.style.display=\"none\";\">&times;</span> "
						+ "  <strong>Error!</strong> Username already exists" + "</div>");
				RequestDispatcher rd = request.getRequestDispatcher("HomePage.jsp");
				rd.forward(request, response);
				// System.out.println("evaluating");

				response.sendRedirect("HomePage.jsp#signup");
			} catch (IllegalStateException ie) {

			}
			return;
		}
		String password = request.getParameter("signUpPassword");
		String address = request.getParameter("signUpAddress");
		String email = request.getParameter("signUpEmail");
		String mobileno = request.getParameter("signUpMobileNo");
		try {
			Users user = new Users(0, username, password, firstName, address, mobileno, email);
			new UsersDao().create(user);
			request.setAttribute("Notification", "<div class=\"col-md-12 alert-success-message\">"
					+ "  <span class=\"alert-closebtn\" onclick=\"this.parentElement.style.display=\"none\";\">&times;</span> "
					+ "  <strong>Successfully Registered!</strong> You can now <a href=\"LogInPage.jsp\">log in.</a>"
					+ "</div>");
			RequestDispatcher rd = request.getRequestDispatcher("HomePage.jsp");
			rd.forward(request, response);
			response.sendRedirect("HomePage.jsp#signup");
		} catch (Exception e) {

		}
	}

}

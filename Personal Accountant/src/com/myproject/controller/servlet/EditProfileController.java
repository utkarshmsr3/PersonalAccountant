package com.myproject.controller.servlet;

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
 * Servlet implementation class EditProfileController
 */
@WebServlet("/EditProfileController")
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfileController() {
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

	protected void updateRequestAttributes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("currentUser");
		Users user = new UsersDao().find(userId);
		//System.out.println(user);
		try {
			request.setAttribute("firstName", user.getName().split(" ")[0]);
			request.setAttribute("lastName", user.getName().substring(user.getName().indexOf(' ')));
		} catch (Exception e) {
			request.setAttribute("firstName", user.getName());
		}
		request.setAttribute("username", user.getUsername());
		request.setAttribute("mobileno", user.getMobile());
		request.setAttribute("password", user.getPassword());
		request.setAttribute("address", user.getAddress());
		request.setAttribute("email", user.getEmail());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("In controller");
		if (request.getParameter("isFormSubmitted") != null) {
			//System.out.println("form submitted");
			HttpSession session = request.getSession();
			Users temp = new UsersDao().find((int)session.getAttribute("currentUser"));
			//System.out.println(temp);
			String name = request.getParameter("inputFirstName") +" " + request.getParameter("inputLastName");
			String username = request.getParameter("inputUsername");
			//System.out.println("username = " + username);
			String newPassword = request.getParameter("inputNewPassword");
			if (!username.equals(temp.getUsername())) {
				//System.out.println("username are different");
				if (new UsersDao().checkAvailability(username)) {
					request.setAttribute("usernameError", " (*Username already exists)");
				}
			} 
				String email = request.getParameter("inputEmail");
				String mobileno = request.getParameter("inputMobileNo");

				String address = request.getParameter("inputAddress");

				Users user = new Users(temp.getUserId(), username, newPassword, name, address, mobileno, email);
				//System.out.println(user);
				new UsersDao().edit(user);			
		}
		updateRequestAttributes(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("EditProfile.jsp");
		rd.forward(request, response);
	}

}

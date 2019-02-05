package com.myproject.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myproject.jdbc.daos.UsersDao;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInServlet() {
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
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("logInUsername");
		String password = request.getParameter("logInPassword");
		String rememberMe = request.getParameter("cc");
		int userId = new UsersDao().giveUserId(username, password);
		HttpSession session = request.getSession();
		if(session.getAttribute("currentUser")!=null && (int)session.getAttribute("currentUser")!=-1){
			userId = (int)session.getAttribute("currentUser");
		}
		for (Cookie c : request.getCookies()) {
			if (c.getName().equals("currentUser")) {
				userId = Integer.parseInt(c.getValue());
			}
		}
		if (userId != -1) {
			if(session.getAttribute("currentUser")==null || (int)session.getAttribute("currentUser")==-1) {
				session.setAttribute("lastlogin", new UsersDao().getLastLogIn(userId));
				new UsersDao().updateLastLogIn(userId, new java.util.Date());}
			session.setAttribute("currentUser", userId);
			if (rememberMe != null) {
				Cookie cook = new Cookie("currentUser", "" + userId);
				cook.setMaxAge(60);
				response.addCookie(cook);
			}
			response.sendRedirect("DashboardController");
		} else {
			request.setAttribute("LogInError", "<div class=\"col-md-12 alert-error-message\">"
					+ "  <span class=\"alert-closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> "
					+ "  <strong>Error!</strong> Username or password is incorrect" + "</div>");
			RequestDispatcher rd = request.getRequestDispatcher("LogInPage.jsp");
			rd.forward(request, response);
		}

	}

}

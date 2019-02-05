package com.myproject.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	private ServletContext context;

	/**
	 * Default constructor.
	 */
	public AuthenticationFilter() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		this.context.log("Requested Resource::" + uri);

		if (uri.endsWith("index.jsp") ||uri.endsWith("HomePage.jsp") || uri.endsWith("SignUpPage.jsp") || uri.endsWith("SignUpServlet")
				|| uri.endsWith("LogInServlet") || uri.endsWith("js") || uri.endsWith("jpg") || uri.endsWith("css")) {
			this.context.log("Home Page Access");
			chain.doFilter(request, response);
		} else {
			HttpSession session = req.getSession();
			int userId = -1;
			if (session.getAttribute("currentUser") != null) {
				userId = (int) session.getAttribute("currentUser");
			}
			if (userId != -1 && uri.endsWith("LogInPage.jsp")) {
				res.sendRedirect("DashboardController");
			} else if (userId != -1) {
				session.setAttribute("currentUser", userId);
				chain.doFilter(req, res);
			} else if(uri.endsWith("LogInPage.jsp")) {
				chain.doFilter(req, res);
			}
			else {
				res.sendRedirect("LogInServlet");
			}

		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

}
